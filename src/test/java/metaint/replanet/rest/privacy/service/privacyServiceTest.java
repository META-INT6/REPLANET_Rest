package metaint.replanet.rest.privacy.service;

import metaint.replanet.rest.org.service.OrgService;
import metaint.replanet.rest.privacy.dto.MemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class privacyServiceTest {

    @Autowired
    PrivacyService privacyService;

    @Autowired
    OrgService orgService;

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

    @Test
    @DisplayName("기부처 기존 정보 가져오기 service 테스트")
    void selectOrgInformation(){
        //given
        int memberCode = 9;

        //when
        List<Map<String, Object>> orgInformation = orgService.selectOrgInformation(memberCode);

        //then
        Assertions.assertNotNull(orgInformation);
        System.out.println("기존 정보 확인 : " + orgInformation);
    }
}
