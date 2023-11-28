package metaint.replanet.rest.privacy.repository;

import metaint.replanet.rest.org.repository.OrgRepository;
import metaint.replanet.rest.privacy.dto.MemberDTO;
import metaint.replanet.rest.privacy.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class privacyRepositoryService {

    @Autowired
    private PrivacyRepository privacyRepository;

    @Autowired
    private OrgRepository orgRepository;

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

    @Test
    @DisplayName("기부처 기존 정보 가져오기 repository 테스트")
    void selectOrgInformation() {
        //given
        int memberCode = 9;

        //when
        List<Object[]> orgInfo = orgRepository.selectOrgInformation(memberCode);
        List<Map<String, Object>> orgInformation = new ArrayList<>();
        for(Object[] info : orgInfo){
            Map<String, Object> orgInformationMap = new HashMap<>();
            orgInformationMap.put("orgEmail", info[0]);
            orgInformationMap.put("password", info[1]);
            orgInformationMap.put("orgName", info[2]);
            orgInformationMap.put("phone", info[3]);
            orgInformationMap.put("description", info[4]);
            orgInformation.add(orgInformationMap);
        }

        //then
        Assertions.assertNotNull(orgInformation);
        System.out.println("기부처 정보 확인해라 ~ : " + orgInformation);
    }
}
