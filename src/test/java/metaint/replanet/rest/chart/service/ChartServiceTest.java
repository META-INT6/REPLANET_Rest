package metaint.replanet.rest.chart.service;

import metaint.replanet.rest.chart.dto.CountAndSumByCategoryDTO;
import metaint.replanet.rest.chart.dto.CountAndSumByMonthlyDTO;
import metaint.replanet.rest.chart.dto.DonationByTimeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ChartServiceTest {

    @Autowired
    private ChartService chartService;

    @DisplayName("캠페인 수 카운트 조회 테스트")
    @Test
    public void testCountCampaign() {
        //given
        int expectResult = 3;
        //when
        int countResult = chartService.countCampaign();
        //then
        Assertions.assertEquals(expectResult, countResult);
    }

    @DisplayName("카테고리별 캠페인 수 카운트, 현재모금액 합계, 목표모금액 합계 조회 테스트")
    @Test
    public void testCountAndSumByCampaignCategory() {
        //when
        List<CountAndSumByCategoryDTO> resultList = chartService.countAndSumByCampaignCategory();
        //then
        Assertions.assertNotNull(resultList);
        // resultList.forEach(System.out::println);
    }
    @DisplayName("당해 등록된 캠페인 수 카운트, 현재모금액 합계, 목표모금액 합계 조회 테스트")
    @Test
    public void testCountAndSumByCurrentyear() {
        //when
        List<CountAndSumByMonthlyDTO> resultList = chartService.countAndSumByCurrentyear();
        //then
        Assertions.assertNotNull(resultList);
        // resultList.forEach(System.out::println);
    }
    @DisplayName("전해 등록된 캠페인 수 카운트, 현재모금액 합계, 목표모금액 합계 조회 테스트")
    @Test
    public void testCountAndSumByPreviousyear() {
        //when
        List<CountAndSumByMonthlyDTO> resultList = chartService.countAndSumByPreviousyear();
        //then
        Assertions.assertNotNull(resultList);
        Assertions.assertTrue(resultList.isEmpty());
        // resultList.forEach(System.out::println);
    }

    @DisplayName("시간별 캠페인 기부금 추이 조회 테스트")
    @Test
    public void testSelectDonationByTime() {
        //when
        List<DonationByTimeDTO> resultList = chartService.selectDonationByTime();
        //then
        Assertions.assertNotNull(resultList);
        resultList.forEach(System.out::println);
    }
}
