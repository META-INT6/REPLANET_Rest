package metaint.replanet.rest.reviews.dto;

import metaint.replanet.rest.reviews.entity.Campaign;

import java.sql.Date;

public class ReviewDTO{

    private Long campaignRevCode;
    private String reviewTitle;
    private String description;

    private Long campaignCampaignCode;
    private String campaignTitle;
    private String campaignContent;
    private Date campaignStartDate;
    private Date campaignEndDate;
    private String campaignCategory;
    private Integer campaignCurrentBudget;
    private Integer campaignGoalBudget;
    private String campaignOrgName;
    private String campaignOrgDescription;
    private String campaignOrgTel;

    public ReviewDTO() {
    }

    public ReviewDTO(Long campaignRevCode, String reviewTitle, String description, Long campaignCampaignCode, String campaignTitle, String campaignContent, Date campaignStartDate, Date campaignEndDate, String campaignCategory, Integer campaignCurrentBudget, Integer campaignGoalBudget, String campaignOrgName, String campaignOrgDescription, String campaignOrgTel) {
        this.campaignRevCode = campaignRevCode;
        this.reviewTitle = reviewTitle;
        this.description = description;
        this.campaignCampaignCode = campaignCampaignCode;
        this.campaignTitle = campaignTitle;
        this.campaignContent = campaignContent;
        this.campaignStartDate = campaignStartDate;
        this.campaignEndDate = campaignEndDate;
        this.campaignCategory = campaignCategory;
        this.campaignCurrentBudget = campaignCurrentBudget;
        this.campaignGoalBudget = campaignGoalBudget;
        this.campaignOrgName = campaignOrgName;
        this.campaignOrgDescription = campaignOrgDescription;
        this.campaignOrgTel = campaignOrgTel;
    }

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

    public Long getCampaignCampaignCode() {
        return campaignCampaignCode;
    }

    public void setCampaignCampaignCode(Long campaignCampaignCode) {
        this.campaignCampaignCode = campaignCampaignCode;
    }

    public String getCampaignTitle() {
        return campaignTitle;
    }

    public void setCampaignTitle(String campaignTitle) {
        this.campaignTitle = campaignTitle;
    }

    public String getCampaignContent() {
        return campaignContent;
    }

    public void setCampaignContent(String campaignContent) {
        this.campaignContent = campaignContent;
    }

    public Date getCampaignStartDate() {
        return campaignStartDate;
    }

    public void setCampaignStartDate(Date campaignStartDate) {
        this.campaignStartDate = campaignStartDate;
    }

    public Date getCampaignEndDate() {
        return campaignEndDate;
    }

    public void setCampaignEndDate(Date campaignEndDate) {
        this.campaignEndDate = campaignEndDate;
    }

    public String getCampaignCategory() {
        return campaignCategory;
    }

    public void setCampaignCategory(String campaignCategory) {
        this.campaignCategory = campaignCategory;
    }

    public Integer getCampaignCurrentBudget() {
        return campaignCurrentBudget;
    }

    public void setCampaignCurrentBudget(Integer campaignCurrentBudget) {
        this.campaignCurrentBudget = campaignCurrentBudget;
    }

    public Integer getCampaignGoalBudget() {
        return campaignGoalBudget;
    }

    public void setCampaignGoalBudget(Integer campaignGoalBudget) {
        this.campaignGoalBudget = campaignGoalBudget;
    }

    public String getCampaignOrgName() {
        return campaignOrgName;
    }

    public void setCampaignOrgName(String campaignOrgName) {
        this.campaignOrgName = campaignOrgName;
    }

    public String getCampaignOrgDescription() {
        return campaignOrgDescription;
    }

    public void setCampaignOrgDescription(String campaignOrgDescription) {
        this.campaignOrgDescription = campaignOrgDescription;
    }

    public String getCampaignOrgTel() {
        return campaignOrgTel;
    }

    public void setCampaignOrgTel(String campaignOrgTel) {
        this.campaignOrgTel = campaignOrgTel;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "campaignRevCode=" + campaignRevCode +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", description='" + description + '\'' +
                ", campaignCampaignCode=" + campaignCampaignCode +
                ", campaignTitle='" + campaignTitle + '\'' +
                ", campaignContent='" + campaignContent + '\'' +
                ", campaignStartDate=" + campaignStartDate +
                ", campaignEndDate=" + campaignEndDate +
                ", campaignCategory='" + campaignCategory + '\'' +
                ", campaignCurrentBudget=" + campaignCurrentBudget +
                ", campaignGoalBudget=" + campaignGoalBudget +
                ", campaignOrgName='" + campaignOrgName + '\'' +
                ", campaignOrgDescription='" + campaignOrgDescription + '\'' +
                ", campaignOrgTel='" + campaignOrgTel + '\'' +
                '}';
    }
}


