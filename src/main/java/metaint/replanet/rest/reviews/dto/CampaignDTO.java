package metaint.replanet.rest.reviews.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
public class CampaignDTO {

    private Long campaignCode;
    private String campaignTitle;
    private String campaignContent;
    private String startDate;
    private String endDate;
    private String campaignCategory;
    private Integer currentBudget;
    private Integer goalBudget;
    private String orgName;
    private String orgDescription;
    private String orgTel;



}
