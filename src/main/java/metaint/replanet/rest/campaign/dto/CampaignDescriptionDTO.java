package metaint.replanet.rest.campaign.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDateTime;

public class CampaignDescriptionDTO {

    private int campaignCode; // 모금 코드

    private String campaignTitle; // 모금 제목
    private String campaignContent; // 모금 내용
    private LocalDateTime startDate; // 모금 시작 일자
    private LocalDateTime endDate; // 모금 마감 일자
    private String campaignCategory; // 모금 카테고리
    private int currentBudget; // 현재 모금액
    private int goalBudget; // 목표 모금액
    private String orgName; // 단체명
    private String orgDescription; //단체 소개
    private String orgTel; // 단체 연락처

    public CampaignDescriptionDTO() {
    }



}