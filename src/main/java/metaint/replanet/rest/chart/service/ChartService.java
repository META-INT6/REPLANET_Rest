package metaint.replanet.rest.chart.service;

import metaint.replanet.rest.chart.dto.CountAndSumByCategoryDTO;
import metaint.replanet.rest.chart.dto.CountAndSumByMonthlyDTO;
import metaint.replanet.rest.chart.dto.DonationByTimeDTO;
import metaint.replanet.rest.chart.repository.ChartRepository;
import metaint.replanet.rest.chart.repository.DonationChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChartService {
    private ChartRepository chartRepository;
    private DonationChartRepository donationChartRepository;


    @Autowired
    public ChartService(ChartRepository chartRepository, DonationChartRepository donationChartRepository) {
        this.chartRepository = chartRepository;
        this.donationChartRepository = donationChartRepository;
    }

    // 캠페인 수 카운트 조회
    public int countCampaign() {

        int countResult = (int) chartRepository.count();

        return countResult;
    }

    // 카테고리별 캠페인 수 카운트, 현재모금액 합계, 목표모금액 합계, 목표까지 남은모금액 조회
    public List<CountAndSumByCategoryDTO> countAndSumByCampaignCategory() {

        List<Object[]> resultList = chartRepository.countAndSumByCategory();

        /*
        resultList.forEach( row -> {
            for(Object item : row) {
                System.out.print(item);
            }
            System.out.println();
        });
        */

        return resultList.stream()
                .map(row -> {
                    String campaignCategory = (String) row[0];
                    int campaigns = ((Number) row[1]).intValue();
                    int sumCurrentBudget = ((Number) row[2]).intValue();
                    int sumGoalBudget = ((Number) row[3]).intValue();
                    int sumExpectBudget = ((Number) row[4]).intValue();

                    CountAndSumByCategoryDTO dto = new CountAndSumByCategoryDTO();
                    dto.setCampaignCategory(campaignCategory);
                    dto.setCampaigns(campaigns);
                    dto.setSumCurrentBudget(sumCurrentBudget);
                    dto.setSumGoalBudget(sumGoalBudget);
                    dto.setSumExpectBudget(sumExpectBudget);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    // 당해 캠페인 수 카운트, 현재모금액 합계, 목표모금액 합계, 목표까지 남은모금액 조회
    public List<CountAndSumByMonthlyDTO> countAndSumByCurrentyear() {
        List<Object[]> resultList = chartRepository.countAndSumByCurrentyear();

        return resultList.stream()
                .map(row -> {
                    String monthly = (String) row[0];
                    int campaigns = ((Number) row[1]).intValue();
                    int sumCurrentBudget = ((Number) row[2]).intValue();
                    int sumGoalBudget = ((Number) row[3]).intValue();
                    int sumExpectBudget = ((Number) row[4]).intValue();

                    CountAndSumByMonthlyDTO dto = new CountAndSumByMonthlyDTO();
                    dto.setMonthly(monthly);
                    dto.setCampaigns(campaigns);
                    dto.setSumCurrentBudget(sumCurrentBudget);
                    dto.setSumGoalBudget(sumGoalBudget);
                    dto.setSumExpectBudget(sumExpectBudget);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // 전해 캠페인 수 카운트, 현재모금액 합계, 목표모금액 합계, 목표까지 남은모금액 조회
    public List<CountAndSumByMonthlyDTO> countAndSumByPreviousyear() {
        List<Object[]> resultList = chartRepository.countAndSumByPreviousyear();

        return resultList.stream()
                .map(row -> {
                    String monthly = (String) row[0];
                    int campaigns = ((Number) row[1]).intValue();
                    int sumCurrentBudget = ((Number) row[2]).intValue();
                    int sumGoalBudget = ((Number) row[3]).intValue();
                    int sumExpectBudget = ((Number) row[4]).intValue();

                    CountAndSumByMonthlyDTO dto = new CountAndSumByMonthlyDTO();
                    dto.setMonthly(monthly);
                    dto.setCampaigns(campaigns);
                    dto.setSumCurrentBudget(sumCurrentBudget);
                    dto.setSumGoalBudget(sumGoalBudget);
                    dto.setSumExpectBudget(sumExpectBudget);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // 시간별 캠페인에 기부된 포인트 및 기부 모금액 조회
    public List<DonationByTimeDTO> selectDonationByTime() {
        List<Object[]> resultList = donationChartRepository.donateByTime();

        return resultList.stream()
                .map(row -> {
                    int campaignCode = ((Number) row[0]).intValue();
                    Date donationDate = (Date) row[1];
                    int donationPoint = ((Number) row[2]).intValue();
                    int payAmount = ((Number) row[3]).intValue();
                    String campaignCategory = (String) row[4];

                    DonationByTimeDTO dto = new DonationByTimeDTO();
                    dto.setCampaignCode(campaignCode);
                    dto.setDonationDate(donationDate);
                    dto.setDonationPoint(donationPoint);
                    dto.setPayAmount(payAmount);
                    dto.setCampaignCategory(campaignCategory);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
