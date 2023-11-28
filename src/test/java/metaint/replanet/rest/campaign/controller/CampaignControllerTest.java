/*
package metaint.replanet.rest.campaign.controller;

import metaint.replanet.rest.campaign.dto.CampaignDescriptionDTO;
import metaint.replanet.rest.campaign.service.CampaignService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

@SpringBootTest
public class CampaignControllerTest {

    @Autowired
    CampaignController campaignController;
    @Autowired
    CampaignService campaignService;

    @DisplayName("기부여부에 따른 캠페인 삭제 테스트")
    @Test
    void campaignDeleteTest(){
        //given
        int campaignCode = 6;
        //when
        //campaignController.campaignDelete(campaignCode);
        Assertions.assertDoesNotThrow(
                () -> campaignController.campaignDelete(campaignCode)
        );
    }

    @DisplayName("기부여부에 따른 캠페인 수정 테스트")
    @Test
    void campaignModifyest(){
        //given
        MultipartFile mFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return "오리진파일네임.jpg";
            }

            @Override
            public String getContentType() {
                return null;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };
        CampaignDescriptionDTO campaign = new CampaignDescriptionDTO(
                6,
                "진행중",
                "도와주세요~섬바리헤업미",
                //LocalDateTime.of(2023, 1, 31, 12, 30, 0),
                "지구촌",
                "0",
                "200,000",
                "하이미디어",
                "안녕 매체",
                "02-121-5678"
        );
        //when
        //campaignController.campaignDelete(campaignCode);
        Assertions.assertDoesNotThrow(
                () -> campaignController.campaignModify(campaign,mFile)
        );
    }
    
    @DisplayName("캠페인 전체 조회")
    @Test
    public void campaignListTest(){
        //given
        String status = "done";
        //when
        campaignController.main(status);
        //then

    }
}
*/
