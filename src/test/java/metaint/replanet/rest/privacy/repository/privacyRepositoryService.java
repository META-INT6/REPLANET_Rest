package metaint.replanet.rest.privacy.repository;

import metaint.replanet.rest.privacy.dto.MemberDTO;
import metaint.replanet.rest.privacy.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class privacyRepositoryService {

    @Autowired
    private PrivacyRepository privacyRepository;

    @Test
    @DisplayName("개인정보 제공 동의 repository 테스트")
    void updateMemberPrivacy(){
        //given
        MemberDTO member = new MemberDTO();
        member.setMemberCode(1);
        member.setPrivacyStatus('Y');
        member.setResidentNum("1234561234567");

        //when
        Member memberPrivacy = privacyRepository.findById(member.getMemberCode()).get();
        memberPrivacy = memberPrivacy.privacyStatus(member.getPrivacyStatus())
                .residentNum(member.getResidentNum())
                .builder();
        privacyRepository.save(memberPrivacy);

        //then
        Assertions.assertNotNull(memberPrivacy);
    }
}
