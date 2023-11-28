package metaint.replanet.rest.chart.dto;

public class CountAndSumByCategoryDTO {

    private String campaignCategory;
    private int campaigns; // 캠페인 수 카운트
    private int sumCurrentBudget;
    private int sumGoalBudget;
    private int displaySumCurrentBudget;

    public CountAndSumByCategoryDTO() {
    }

    public CountAndSumByCategoryDTO(String campaignCategory, int campaigns, int sumCurrentBudget, int sumGoalBudget, int displaySumCurrentBudget) {
        this.campaignCategory = campaignCategory;
        this.campaigns = campaigns;
        this.sumCurrentBudget = sumCurrentBudget;
        this.sumGoalBudget = sumGoalBudget;
        this.displaySumCurrentBudget = displaySumCurrentBudget;
    }

    public String getCampaignCategory() {
        return campaignCategory;
    }

    public void setCampaignCategory(String campaignCategory) {
        this.campaignCategory = campaignCategory;
    }

    public int getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(int campaigns) {
        this.campaigns = campaigns;
    }

    public int getSumCurrentBudget() {
        return sumCurrentBudget;
    }

    public void setSumCurrentBudget(int sumCurrentBudget) {
        this.sumCurrentBudget = sumCurrentBudget;
    }

    public int getSumGoalBudget() {
        return sumGoalBudget;
    }

    public void setSumGoalBudget(int sumGoalBudget) {
        this.sumGoalBudget = sumGoalBudget;
    }

    public int getDisplaySumCurrentBudget() {
        return displaySumCurrentBudget;
    }

    public void setDisplaySumCurrentBudget(int displaySumCurrentBudget) {
        this.displaySumCurrentBudget = displaySumCurrentBudget;
    }

    @Override
    public String toString() {
        return "CountAndSumByCategoryDTO{" +
                "campaignCategory='" + campaignCategory + '\'' +
                ", campaigns=" + campaigns +
                ", sumCurrentBudget=" + sumCurrentBudget +
                ", sumGoalBudget=" + sumGoalBudget +
                ", displaySumCurrentBudget=" + displaySumCurrentBudget +
                '}';
    }
}
