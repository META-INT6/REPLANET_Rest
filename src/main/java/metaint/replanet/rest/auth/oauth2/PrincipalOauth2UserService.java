package metaint.replanet.rest.auth.oauth2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.auth.entity.Member;
import metaint.replanet.rest.auth.entity.MemberRole;
import metaint.replanet.rest.auth.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes : {}", oAuth2User.getAttributes());

        OAuth2UserInfo oAuth2UserInfo = null;

        String provider = userRequest.getClientRegistration().getRegistrationId();

        if(provider.equals("kakao")) {
            log.info("카카오 로그인 요청");
            oAuth2UserInfo = new KakaoUserInfo( (Map)oAuth2User.getAttributes() );
        }

        String providerId = oAuth2UserInfo.getProviderId();

        String email = oAuth2UserInfo.getEmail();


        Optional<Member> optionalMember = memberRepository.findByEmail(email);
        Member member = null;

        if(optionalMember.isEmpty()) {
            member = Member.builder()
                    .provider(provider)
                    .providerId(providerId)
                    .memberRole(MemberRole.ROLE_USER)
                    .build();
            memberRepository.save(member);
        } else {
            member = optionalMember.get();
        }

        return new PrincipalDetails(member, oAuth2User.getAttributes());
    }
}