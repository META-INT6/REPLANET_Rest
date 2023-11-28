package metaint.replanet.rest.campaign.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CampaignDesOrgDTO {

    private int campaignCode; // 모금 코드
    private String campaignTitle; // 모금 제목
    private String campaignContent; // 모금 내용
    private LocalDateTime startDate; // 모금 시작 일자
    private LocalDateTime endDate; // 모금 마감 일자
    private String campaignCategory; // 모금 카테고리
    private String currentBudget; // 현재 모금액
    private String goalBudget; // 목표 모금액
    
    private List<CampaignDescFileDTO> campaignDescFileList; // 사진 파일

    private OrgDTO organization; // 기부처 코드


}
