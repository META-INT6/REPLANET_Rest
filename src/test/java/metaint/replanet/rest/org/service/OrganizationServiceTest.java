package metaint.replanet.rest.org.service;

import metaint.replanet.rest.org.dto.OrgRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrganizationServiceTest {

    @Autowired
    private OrgService orgService;

    @DisplayName("기부처 등록")
    @Test
    public void orgRegistTest(){
        //given
        OrgRequestDTO orgRequestDTO = new OrgRequestDTO(
                "아이디",
                "비번",
                "기부처명"
        );
        //when
        orgService.orgRegist(orgRequestDTO);
        //then
    }


}
