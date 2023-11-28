package metaint.replanet.rest.reviews.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity(name = "reviewPkg_entityCampaign")
@Table(name = "tbl_campaign_description")
public class Campaign {

    @Id
    @Column(name = "campaign_code")
    private Long campaignCode;

    @Column(name = "campaign_title")
    private String campaignTitle;

    @Column(name = "campaign_content")
    private String campaignContent;

    @Column(name = "start_date")
    private java.sql.Date startDate;

    @Column(name = "end_date")
    private java.sql.Date endDate;

    @Column(name = "campaign_category")
    private String campaignCategory;

    @Column(name = "current_budget")
    private Integer currentBudget;

    @Column(name = "goal_budget")
    private Integer goalBudget;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_description")
    private String orgDescription;

    @Column(name = "org_tel")
    private String orgTel;


    // Constructors
    public Campaign() {
    }

    public Campaign(String campaignTitle, String campaignContent, java.sql.Date startDate, java.sql.Date endDate, String campaignCategory, Integer currentBudget, Integer goalBudget, String orgName, String orgDescription, String orgTel) {
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

    // Getters and Setters
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

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
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

    // toString method
    @Override
    public String toString() {
        return "Campaign{" +
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
