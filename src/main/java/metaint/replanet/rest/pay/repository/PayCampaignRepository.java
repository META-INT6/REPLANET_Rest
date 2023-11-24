package metaint.replanet.rest.pay.repository;

import metaint.replanet.rest.pay.entity.CampaignDescription;
import metaint.replanet.rest.pay.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface PayCampaignRepository extends JpaRepository<CampaignDescription, Integer> {

    @Modifying
    @Query("UPDATE pay_campaign_description c SET c.currentBudget = c.currentBudget + (:donationPoint + :payAmount) WHERE c.campaignCode = :campaignCode")
    void updateCurrentBudget(@Param("donationPoint") int donationPoint, @Param("payAmount") int payAmount, @Param("campaignCode") int campaignCode);
}
