package metaint.replanet.rest.chart.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "tblCampaignDescription")
@Table(name = "tbl_campaign_description")
public class CampaignDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_code")
    private int campaignCode;
    @Column(name = "campaign_title")
    private String campaignTitle;
    @Column(name = "campaign_content")
    private String campaignContent;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "campaign_category")
    private String campaignCategory;
    @Column(name = "current_budget")
    private int currentBudget;
    @Column(name = "goal_budget")
    private int goalBudget;
    @Column(name = "org_name")
    private String orgName;
    @Column(name = "org_description")
    private String orgDescription;
    @Column(name = "org_tel")
    private String orgTel;

    protected CampaignDescription() {}

    public CampaignDescription(int campaignCode, String campaignTitle, String campaignContent, LocalDateTime startDate, LocalDateTime endDate, String campaignCategory, int currentBudget, int goalBudget, String orgName, String orgDescription, String orgTel) {
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

    public int getCampaignCode() {
        return campaignCode;
    }

    public String getCampaignTitle() {
        return campaignTitle;
    }

    public String getCampaignContent() {
        return campaignContent;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public String getCampaignCategory() {
        return campaignCategory;
    }

    public int getCurrentBudget() {
        return currentBudget;
    }

    public int getGoalBudget() {
        return goalBudget;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getOrgDescription() {
        return orgDescription;
    }

    public String getOrgTel() {
        return orgTel;
    }

    @Override
    public String toString() {
        return "CampaignDescription{" +
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
