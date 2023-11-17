package metaint.replanet.rest.reviews.dto;

import lombok.*;
import metaint.replanet.rest.reviews.entity.Campaign;

import java.sql.Date;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO{

    private Long reviewCode;
    private String reviewTitle;
    private String description;
    private Long campaignCode;

   /* private String campaignTitle;
    private String campaignContent;
    private Date startDate;
    private Date endDate;
    private String campaignCategory;
    private Integer currentBudget;
    private Integer goalBudget;
    private String orgName;
    private String orgDescription;
    private String orgTel;
*/
}


