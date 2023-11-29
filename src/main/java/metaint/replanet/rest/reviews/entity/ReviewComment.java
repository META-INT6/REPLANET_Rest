package metaint.replanet.rest.reviews.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(name = "rev_comment_monitorized", insertable = false)
    private String revCommentMonitorized = "N";

    public ReviewComment reviewCommentCode(Long revCommentCode) {
        this.revCommentCode = revCommentCode;
        return this;
    }

    public ReviewComment revCommentContent(String revCommentContent) {
        this.revCommentContent = revCommentContent;
        return this;
    }

    public ReviewComment memberCode(String memberCode) {
        this.memberCode = memberCode;
        return this;
    }

    public ReviewComment revCommentDate(LocalDateTime revCommentDate) {
        this.revCommentDate = revCommentDate;
        return this;
    }

    public ReviewComment reviewCode(Long reviewCode) {
        this.reviewCode = reviewCode;
        return this;
    }


    public ReviewComment revCommentMonitorized(String revCommentMonitorized) {
        this.revCommentMonitorized = revCommentMonitorized;
        return this;
    }
    public ReviewComment build() {
        return new ReviewComment(revCommentCode, revCommentContent, memberCode, revCommentDate, reviewCode, revCommentMonitorized);
    }


}
