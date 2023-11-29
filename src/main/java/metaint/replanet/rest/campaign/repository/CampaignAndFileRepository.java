package metaint.replanet.rest.campaign.repository;

import metaint.replanet.rest.campaign.entity.CampaignAndFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CampaignAndFileRepository extends JpaRepository<CampaignAndFile, Integer> {
    List<CampaignAndFile> findByEndDateAfter(LocalDateTime currentDate);

    List<CampaignAndFile> findByEndDateBefore(LocalDateTime currentDate);

//    @Query(value = "SELECT *  FROM  tbl_campaign_description d  RIGHT JOIN  tbl_campaign_desc_file cf on d.campaign_code = cf.campaign_code WHERE d.end_date > now() "
//           ,nativeQuery = true)
//    List<CampaignAndFile> findByEndDateAfter1(LocalDateTime currentDate);
}
