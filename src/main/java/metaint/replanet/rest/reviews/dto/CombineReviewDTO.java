package metaint.replanet.rest.reviews.dto;

import lombok.*;
import metaint.replanet.rest.reviews.entity.Organization;
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

    private Long reviewCode;
    private String reviewTitle;
    private String reviewDescription;
    private Long reviewCampaignCode;

    private CampaignDTO campaign;

    private List<ReviewFileDTO> reviewFileList;
    private List<ReviewComment> reviewCommentList;

}