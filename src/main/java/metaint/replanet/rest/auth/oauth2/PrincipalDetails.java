package metaint.replanet.rest.auth.oauth2;

import metaint.replanet.rest.auth.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class PrincipalDetails implements UserDetails, OAuth2User {

    private Member member;

    public PrincipalDetails(Member member, Map<String, Object> attributes) {
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }



    // 권한 관련 작업을 하기 위한 role return
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collections = new ArrayList<>();
        collections.add(() -> {
            return member.getMemberRole().name();
        });

        return collections;
    }

    // get Password 메서드
    @Override
    public String getPassword() {
        return member.getPassword();
    }

    // get Username 메서드 (생성한 User은 loginId 사용)
    @Override
    public String getUsername() {
        return member.getEmail();
    }

    // 계정이 만료 되었는지 (true: 만료X)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겼는지 (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되었는지 (true: 만료X)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화(사용가능)인지 (true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }
}