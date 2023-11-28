package metaint.replanet.rest.reviews.entity;

import metaint.replanet.rest.reviews.entity.Campaign;

import javax.persistence.*;

@Entity(name = "reviewPkg_entityReview")
@Table(name = "tbl_campaign_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_rev_code")
    private Long campaignRevCode;

    @Column(name = "review_title")
    private String reviewTitle;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "campaign_code")
    private Campaign campaign;

    // Constructors
    protected Review() {
    }

    public Review(String reviewTitle, String description, Campaign campaign) {
        this.reviewTitle = reviewTitle;
        this.description = description;
        this.campaign = campaign;
    }

    // Getters and Setters
    public Long getCampaignRevCode() {
        return campaignRevCode;
    }

    public void setCampaignRevCode(Long campaignRevCode) {
        this.campaignRevCode = campaignRevCode;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    // toString method
    @Override
    public String toString() {
        return "Review{" +
                "campaignRevCode=" + campaignRevCode +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", description='" + description + '\'' +
                ", campaign=" + campaign +
                '}';
    }
}
