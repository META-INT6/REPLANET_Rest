package metaint.replanet.rest.reviews.dto;

import lombok.*;
import metaint.replanet.rest.reviews.entity.ReviewComment;
import metaint.replanet.rest.reviews.entity.ReviewFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CombineReviewDTO {

    private Long campaignCode;
    private String campaignTitle;
    private String campaignContent;
    private String campaignStartDate;
    private String campaignEndDate;
    private String campaignCategory;
    private Integer campaignCurrentBudget;
    private Integer campaignGoalBudget;
    private String campaignOrgName;
    private String campaignOrgDescription;
    private String campaignOrgTel;
    private Long reviewCode;
    private String reviewTitle;
    private String reviewDescription;
    private Long reviewCampaignCode;
    private List<ReviewFile> reviewFileList;
    private List<ReviewComment> reviewCommentList;

}