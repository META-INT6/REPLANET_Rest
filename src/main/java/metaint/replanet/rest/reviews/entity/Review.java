package metaint.replanet.rest.reviews.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import metaint.replanet.rest.reviews.entity.Campaign;

import javax.persistence.*;
import java.util.List;

@Entity(name = "reviewPkg_entityReview")
@Table(name = "tbl_review")
@NoArgsConstructor
@Getter
@ToString
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_code")
    private Long reviewCode;

    @Column(name = "review_title")
    private String reviewTitle;

    @Column(name = "description")
    private String description;

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "campaign_code", referencedColumnName = "campaign_code")
    private Campaign campaign;

    @OneToMany(fetch=FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "review_code", referencedColumnName = "review_code")
    private List<ReviewFile> reviewFileList;

    public Review(Long reviewCode, String reviewTitle, String description) {
    }

    public Review reviewCode(Long reviewCode) {
        this.reviewCode = reviewCode;
        return this;
    }
    public Review reviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
        return this;
    }

    public Review description(String description) {
        this.description = description;
        return this;
    }

    public Review build() {
        return new Review(reviewCode, reviewTitle, description);
    }
}