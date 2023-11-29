package metaint.replanet.rest.campaign.repository;

import metaint.replanet.rest.campaign.entity.CampaignDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignDescription, Integer> {

    @Query(value = "SELECT * FROM tbl_campaign_description WHERE end_date >= current_date ORDER BY end_date desc", nativeQuery = true)
    List<CampaignDescription> findCampaignSort(Date currentDate);

    List<CampaignDescription> findByCampaignCategory(String category);

}
