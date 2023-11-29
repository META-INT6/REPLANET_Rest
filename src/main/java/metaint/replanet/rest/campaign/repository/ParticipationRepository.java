package metaint.replanet.rest.campaign.repository;

import metaint.replanet.rest.campaign.entity.Donation;
import metaint.replanet.rest.campaign.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParticipationRepository extends JpaRepository<Pay, Integer> {


    @Query(value = "SELECT   p.pay_code, " +
            "p.donation_code, " +
            "p.pay_amount, " +
            "d.donation_code, " +
            "d.donation_date_time, " +
            "d.donation_point, " +
            "d.campaign_code, " +
            "d.member_code, " +
            "c.campaign_code, " +
            "m.member_code, " +
            "m.member_name, " +
            "m.member_role " +
            "FROM tbl_pay p " +
            "LEFT OUTER JOIN tbl_donation d " +
                            "on p.donation_code=d.donation_code " +
            "LEFT OUTER JOIN tbl_campaign_description c " +
                            "on d.campaign_code=c.campaign_code " +
            "LEFT OUTER JOIN tbl_member m " +
                            "on d.member_code=m.member_code " +
            "WHERE d.campaign_code = :campaignCode " +
            "ORDER BY d.donation_date_time DESC "
            , nativeQuery = true)
    List<Pay> findByDonationByCampaignCode(int campaignCode);

    @Query(value = "SELECT " +
            "p.pay_code, " +
            "p.pay_amount, " +
            "DATE_FORMAT(d.donation_date_time, '%Y-%m-%d') as donation_date, " +
            "count(*) , " +
            "sum(p.pay_amount + d.donation_point) " +
            "from " +
            "tbl_pay p " +
            "left outer join " +
            "tbl_donation d " +
            "on p.donation_code = d.donation_code " +
            "left outer join " +
            "tbl_campaign_description c " +
            "on d.campaign_code = c.campaign_code " +
            "left outer join " +
            "tbl_member m " +
            "on d.member_code = m.member_code " +
            "where DATE_FORMAT(d.donation_date_time, '%Y-%m-%d') = DATE_FORMAT(now(), '%Y-%m-%d')",
            nativeQuery = true)
    List<Object[]>findByTodayDonation();
}
