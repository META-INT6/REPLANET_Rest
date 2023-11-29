package metaint.replanet.rest.chart.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "chartDonation")
@Table(name = "tbl_donation")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_code")
    private int donationCode;
    @Column(name = "donation_date_time")
    private LocalDateTime donationDateTime;
    @Column(name = "donation_point")
    private int donationPoint;
    @JoinColumn(name = "member_code")
    @ManyToOne
    private Member refMember;
    @JoinColumn(name = "campaign_code")
    @ManyToOne
    private CampaignDescription refCampaign;

    protected Donation() {}

    public Donation(int donationCode, LocalDateTime donationDateTime, int donationPoint, Member refMember, CampaignDescription refCampaign) {
        this.donationCode = donationCode;
        this.donationDateTime = donationDateTime;
        this.donationPoint = donationPoint;
        this.refMember = refMember;
        this.refCampaign = refCampaign;
    }

    public int getDonationCode() {
        return donationCode;
    }

    public LocalDateTime getDonationDateTime() {
        return donationDateTime;
    }

    public int getDonationPoint() {
        return donationPoint;
    }

    public Member getRefMember() {
        return refMember;
    }

    public CampaignDescription getRefCampaign() {
        return refCampaign;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "donationCode=" + donationCode +
                ", donationDateTime=" + donationDateTime +
                ", donationPoint=" + donationPoint +
                ", refMember=" + refMember +
                ", refCampaign=" + refCampaign +
                '}';
    }
}
