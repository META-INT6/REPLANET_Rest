package metaint.replanet.rest.campaign.repository;

import metaint.replanet.rest.campaign.entity.CampaignDescFile;
import metaint.replanet.rest.campaign.entity.CampaignDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CampaignFileRepository extends JpaRepository<CampaignDescFile, Integer> {

    List<CampaignDescFile> findByCampaignCodeCampaignCode(int campaignCode);

    void deleteByCampaignCodeCampaignCode(int campaignCode);

}
