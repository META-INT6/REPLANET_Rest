package metaint.replanet.rest.reviews.dto;

import lombok.*;

import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ReviewFileCombineDTO {

    private Long campaignCode;
    private String campaignTitle;
    private String campaignContent;
    private Date startDate;
    private Date endDate;
    private String campaignCategory;
    private Integer currentBudget;
    private Integer goalBudget;
    private String orgName;
    private String orgDescription;
    private String orgTel;
    private Long reviewCode;
    private String reviewTitle;
    private String reviewDescription;
    private Long reviewCampaignCode;
    private Long revFileCode;

    private String fileOriginName;

    private String fileOriginPath;

    private String fileSaveName;

    private String fileSavePath;

    private String fileExtension;
   // private Long reviewCampaignCode;


}
