package metaint.replanet.rest.chart.dto;

import java.util.Date;

public class DonationByTimeDTO {

    private int campaignCode;
    private Date donationDate;
    private int donationPoint;
    private int payAmount;
    private String campaignCategory;

    public DonationByTimeDTO() {
    }

    public DonationByTimeDTO(int campaignCode, Date donationDate, int donationPoint, int payAmount, String campaignCategory) {
        this.campaignCode = campaignCode;
        this.donationDate = donationDate;
        this.donationPoint = donationPoint;
        this.payAmount = payAmount;
        this.campaignCategory = campaignCategory;
    }

    public int getCampaignCode() {
        return campaignCode;
    }

    public void setCampaignCode(int campaignCode) {
        this.campaignCode = campaignCode;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    public int getDonationPoint() {
        return donationPoint;
    }

    public void setDonationPoint(int donationPoint) {
        this.donationPoint = donationPoint;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public String getCampaignCategory() {
        return campaignCategory;
    }

    public void setCampaignCategory(String campaignCategory) {
        this.campaignCategory = campaignCategory;
    }

    @Override
    public String toString() {
        return "DonationByTimeDTO{" +
                "campaignCode=" + campaignCode +
                ", donationDate=" + donationDate +
                ", donationPoint=" + donationPoint +
                ", payAmount=" + payAmount +
                ", campaignCategory='" + campaignCategory + '\'' +
                '}';
    }
}
