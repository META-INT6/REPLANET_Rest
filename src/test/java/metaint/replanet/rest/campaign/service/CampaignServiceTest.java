/*
package metaint.replanet.rest.campaign.service;

import metaint.replanet.rest.campaign.dto.CampaignDesOrgDTO;
import metaint.replanet.rest.campaign.dto.CampaignDescriptionDTO;
import metaint.replanet.rest.campaign.entity.Campaign;
import metaint.replanet.rest.campaign.entity.Pay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class CampaignServiceTest {

    @Autowired
    private CampaignService campaignService;

    @DisplayName("캠페인 진행중 전체 조회 테스트")
    @Test
    public void campaignFindAllTest() {
        //when
        List<CampaignDesOrgDTO> findCampaignList = campaignService.findCampaignList();
        //then
        Assertions.assertNotNull(findCampaignList);
        System.out.println(findCampaignList);
        //findCampaignList.forEach(System.out::println);
    }

    @DisplayName("캠페인 종료 전체 조회 테스트")
    @Test
    public void campaignDoneFindAllTest() {
        //when
        List<CampaignDesOrgDTO> findCampaignList = campaignService.findCampaignListDone();
        //then
        Assertions.assertNotNull(findCampaignList);
        findCampaignList.forEach(System.out::println);
    }

    @DisplayName("캠페인 상세 조회 테스트")
    @Test
    public void campaignFindByIdTest() {
        //given
        int campaignCode = 2;

        //when
        Campaign findCampaign = campaignService.findCampaign(campaignCode);

        //then
        Assertions.assertNotNull(findCampaign);
        System.out.println(findCampaign);
    }

    @DisplayName("캠페인 조건 조회 종료 임박 순")
    @Test
    public void campaignFindSortTest() {
        //given
        LocalDateTime currentDate = LocalDateTime.now();

        //when
        List<Campaign> findCampaignSort = campaignService.findCampaignSort(currentDate);

        //then
        Assertions.assertNotNull(findCampaignSort);

        findCampaignSort.forEach(System.out::println);

    }

    @DisplayName("캠페인 등록 테스트")
    @Test
    void campaignRegistTest() {
        //given
        CampaignDescriptionDTO campaign = new CampaignDescriptionDTO(
                "진행중",
                "도와주세요~섬바리헤업미",
                "2021-12-05",
                "지구촌",
                "0",
                "200,000",
                "하이미디어",
                "안녕 매체",
                "02-121-5678"
        );

        //when
        campaignService.registCampaign(campaign);

        //then
//        List<CampaignDescription> foundCampaign = campaignService.findCampaignList();
//        Assertions.assertNotNull(foundCampaign);

    }


    private static Stream<Arguments> createTestData() {
        return Stream.of(
                Arguments.of("원본파일명", "저장파일명", "저장경로", "확장자", 0)
        );
    }

    @DisplayName("캠페인 이미지 등록 테스트")
    @Test
    void campaignImageRegistTest() throws IOException {
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
        int code = 6;
        //when
        //then
        Assertions.assertDoesNotThrow(
                () -> campaignService.registImage(mFile, code)
        );
    }

    @DisplayName("캠페인 삭제 테스트")
    @Test
    void campaignDeleteTest() {
        //given
        int campaignCode = 11;
        //when
        //campaignController.campaignDelete(campaignCode);
        Assertions.assertDoesNotThrow(
                () -> campaignService.deleteCampaign(campaignCode)
        );
    }

    @DisplayName("캠페인 수정 테스트")
    @Test
    void campaignModifyTest() {
        //given
        MultipartFile mFile = new MultipartFile() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getOriginalFilename() {
                return "11수정오리진파일네임22.jpg";
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
                "수정 진행중",
                "수정 도와주세요~섬바리헤업미2",
                "2021-12-05",
                "지구촌",
                "0",
                "200,000",
                " 수정하이미디어",
                "안녕 매체",
                "02-121-5678"
        );
        //when
        //then
        Assertions.assertDoesNotThrow(
                () -> campaignService.modifyCampaign(campaign, 1, mFile)
        );
    }

    @DisplayName("기부내역 조회 테스트")
    @Test
    void donationFindAllByCampaignTest() {
        //given
        int code = 1;
        //when
        List<Pay> donationList = campaignService.findparticipationList(code);
        //then
        Assertions.assertNotNull(donationList);
        donationList.forEach(System.out::println);
    }
    
//    @DisplayName("카테고리별 조회 테스트")
//    @Test
//    void campaignSearchByCategory(){
//        //given
//        String category = "기타";
//        //when
//        List<CampaignDescription> campaignList = campaignService.findCategoryByCampaignList(category);
//        //then
//        Assertions.assertNotNull(campaignList);
//        campaignList.forEach(System.out::println);
//
//    }


}

*/
