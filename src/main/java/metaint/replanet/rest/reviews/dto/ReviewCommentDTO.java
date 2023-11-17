package metaint.replanet.rest.reviews.dto;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCommentDTO {

    private Long revCommentCode;

    private String revCommentContent;

    private String memberCode;

    private LocalDateTime revCommentDate;

    private Long reviewCode;
}
