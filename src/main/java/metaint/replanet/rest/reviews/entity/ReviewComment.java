package metaint.replanet.rest.reviews.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_review_comment")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rev_comment_code")
    private Long revCommentCode;

    @Column(name = "rev_comment_content")
    private String revCommentContent;

    @Column(name = "member_code")
    private String memberCode;

    @Column(name = "rev_comment_date")
    private LocalDateTime revCommentDate;

    @Column(name = "review_code")
    private Long reviewCode;

}
