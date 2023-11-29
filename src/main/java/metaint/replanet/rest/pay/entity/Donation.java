    package metaint.replanet.rest.pay.entity;

    import lombok.*;

    import javax.persistence.*;
    import java.time.LocalDateTime;
    @Entity(name = "pay_donation")
    @Table(name = "tbl_donation")
    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Builder(toBuilder = true, access = AccessLevel.PUBLIC)
    public class Donation {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "donation_code")
        private int donationCode;
        @Column(name = "donation_date_time")
        private LocalDateTime donationDateTime;
        @Column(name = "donation_point")
        private int donationPoint;
        @ManyToOne
        @JoinColumn(name = "member_code")
        private Member refMember;
        @ManyToOne
        @JoinColumn(name = "campaign_code")
        private CampaignDescription refCampaign;

        public Donation(int donationCode, LocalDateTime donationDateTime, int donationPoint, Member refMember, CampaignDescription refCampaign) {
            this.donationCode = donationCode;
            this.donationDateTime = LocalDateTime.now();
            this.donationPoint = donationPoint;
            this.refMember = refMember;
            this.refCampaign = refCampaign;
        }
    }
