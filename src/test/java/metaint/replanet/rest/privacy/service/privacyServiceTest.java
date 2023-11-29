package metaint.replanet.rest.privacy.service;

import metaint.replanet.rest.privacy.dto.MemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class privacyServiceTest {

    @Autowired
    PrivacyService privacyService;

    @Test
    @DisplayName("개인정보 제공 동의 service 테스트")
    void updateMemberPrivacy(){
        //given
        MemberDTO member = new MemberDTO();
        member.setMemberCode(1);
        member.setPrivacyStatus('Y');
        member.setResidentNum("1234561234567");

        //when
        int result = privacyService.updateMemberPrivacy(member);

        //then
        Assertions.assertEquals(result, 1);
    }
}
