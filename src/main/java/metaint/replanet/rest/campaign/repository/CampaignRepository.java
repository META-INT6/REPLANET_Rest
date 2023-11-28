package metaint.replanet.rest.campaign.repository;

import metaint.replanet.rest.campaign.entity.CampaignDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignDescription, Integer> {



    List<CampaignDescription> findByCampaignCategory(String category);

}
