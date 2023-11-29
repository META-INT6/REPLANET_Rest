package metaint.replanet.rest.campaign.entity;

import lombok.*;
import metaint.replanet.rest.pay.entity.CampaignDescription;
import metaint.replanet.rest.pay.entity.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity(name = "campaign_donation")
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
        private Member member;
        @ManyToOne
        @JoinColumn(name = "campaign_code")
        private CampaignDescription campaignDescription;

        public Donation(int donationCode, LocalDateTime donationDateTime, int donationPoint, Member member, CampaignDescription campaignDescription) {
                this.donationCode = donationCode;
                this.donationDateTime = LocalDateTime.now();
                this.donationPoint = donationPoint;
                this.member = member;
                this.campaignDescription = campaignDescription;
        }
}
