package metaint.replanet.rest.reviews.repository;

import metaint.replanet.rest.reviews.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
