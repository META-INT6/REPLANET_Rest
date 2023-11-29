package metaint.replanet.rest.pay.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CampaignDescriptionDTO {
    private int campaignCode;
    private String campaignTitle;
    private String campaignContent;
    private LocalDate startDate;
    private LocalDate endDate;
    private String campaignCategory;
    private int currentBudget;
    private int goalBudget;
    private String orgName;
    private String orgDescription;
    private String orgTel;
}
