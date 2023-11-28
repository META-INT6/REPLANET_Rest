package metaint.replanet.rest.reviews.dto;

public class CombineReviewDTO {

    private Long campaignRevCode;
    private String reviewTitle;
    private String description;
    private Long campaignCode;


    private String orgName;
    private Integer currentBudget;
    public String orgDescription;

    public CombineReviewDTO() {
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

    public Long getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(Long campaignCode) {
        this.campaignCode = campaignCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getCurrentBudget() {
        return currentBudget;
    }

    public void setCurrentBudget(Integer currentBudget) {
        this.currentBudget = currentBudget;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    @Override
    public String toString() {
        return "CombineReviewDTO{" +
                "campaignRevCode=" + campaignRevCode +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", description='" + description + '\'' +
                ", campaignCode=" + campaignCode +
                ", orgName='" + orgName + '\'' +
                ", currentBudget=" + currentBudget +
                ", orgDescription='" + orgDescription + '\'' +
                '}';
    }
}
