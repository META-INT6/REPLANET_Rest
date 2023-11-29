package metaint.replanet.rest.campaign.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CampaignDescriptionDTO {

    private int campaignCode; // 모금 코드
    private String campaignTitle; // 모금 제목
    private String campaignContent; // 모금 내용
    private LocalDateTime startDate; // 모금 시작 일자
    private String endDate; // 모금 마감 일자
    private String campaignCategory; // 모금 카테고리
    private String currentBudget; // 현재 모금액
    private String goalBudget; // 목표 모금액
    private String orgName; // 단체명
    private String orgDescription; //단체 소개
    private String orgTel; // 단체 연락처

    public CampaignDescriptionDTO(String campaignTitle, String campaignContent, String endDate, String campaignCategory, String currentBudget, String goalBudget, String orgName, String orgDescription, String orgTel) {
        this.campaignTitle = campaignTitle;
        this.campaignContent = campaignContent;
        this.endDate = endDate;
        this.campaignCategory = campaignCategory;
        this.currentBudget = currentBudget;
        this.goalBudget = goalBudget;
        this.orgName = orgName;
        this.orgDescription = orgDescription;
        this.orgTel = orgTel;
    }

    public CampaignDescriptionDTO(int campaignCode, String campaignTitle, String campaignContent, String endDate, String campaignCategory, String currentBudget, String goalBudget, String orgName, String orgDescription, String orgTel) {
        this.campaignCode = campaignCode;
        this.campaignTitle = campaignTitle;
        this.campaignContent = campaignContent;
        this.endDate = endDate;
        this.campaignCategory = campaignCategory;
        this.currentBudget = currentBudget;
        this.goalBudget = goalBudget;
        this.orgName = orgName;
        this.orgDescription = orgDescription;
        this.orgTel = orgTel;
    }
}
