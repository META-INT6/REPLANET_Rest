package metaint.replanet.rest.reviews.dto;

import java.util.Date;

public class CampaignDTO {

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

    public CampaignDTO() {
    }

    public CampaignDTO(Long campaignCode, String campaignTitle, String campaignContent, Date startDate, Date endDate, String campaignCategory, Integer currentBudget, Integer goalBudget, String orgName, String orgDescription, String orgTel) {
        this.campaignCode = campaignCode;
        this.campaignTitle = campaignTitle;
        this.campaignContent = campaignContent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campaignCategory = campaignCategory;
        this.currentBudget = currentBudget;
        this.goalBudget = goalBudget;
        this.orgName = orgName;
        this.orgDescription = orgDescription;
        this.orgTel = orgTel;
    }

    public Long getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(Long campaignCode) {
        this.campaignCode = campaignCode;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCampaignCategory() {
        return campaignCategory;
    }

    public void setCampaignCategory(String campaignCategory) {
        this.campaignCategory = campaignCategory;
    }

    public Integer getCurrentBudget() {
        return currentBudget;
    }

    public void setCurrentBudget(Integer currentBudget) {
        this.currentBudget = currentBudget;
    }

    public Integer getGoalBudget() {
        return goalBudget;
    }

    public void setGoalBudget(Integer goalBudget) {
        this.goalBudget = goalBudget;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public void setOrgDescription(String orgDescription) {
        this.orgDescription = orgDescription;
    }

    public String getOrgTel() {
        return orgTel;
    }

    public void setOrgTel(String orgTel) {
        this.orgTel = orgTel;
    }

    @Override
    public String toString() {
        return "CampaignDTO{" +
                "campaignCode=" + campaignCode +
                ", campaignTitle='" + campaignTitle + '\'' +
                ", campaignContent='" + campaignContent + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", campaignCategory='" + campaignCategory + '\'' +
                ", currentBudget=" + currentBudget +
                ", goalBudget=" + goalBudget +
                ", orgName='" + orgName + '\'' +
                ", orgDescription='" + orgDescription + '\'' +
                ", orgTel='" + orgTel + '\'' +
                '}';
    }
}
