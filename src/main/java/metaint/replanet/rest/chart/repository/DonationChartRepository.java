package metaint.replanet.rest.chart.repository;

import metaint.replanet.rest.chart.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationChartRepository extends JpaRepository<Donation, Integer> {

    @Query(value = "SELECT d.campaign_code, d.donation_date_time AS donation_time, d.donation_point, COALESCE(p.pay_amount, 0) AS pay_amount, COALESCE(c.campaign_category, \"미설정\") AS campaign_category " +
            "FROM tbl_donation d " +
            "LEFT JOIN tbl_pay p ON(d.donation_code = p.donation_code) " +
            "LEFT JOIN tbl_campaign_description c ON(d.campaign_code = c.campaign_code) " +
            "GROUP BY donation_time, d.campaign_code"
            , nativeQuery = true)
    List<Object[]> donateByTime();
}
