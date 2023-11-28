package metaint.replanet.rest.chart.repository;

import metaint.replanet.rest.chart.entity.CampaignDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartRepository extends JpaRepository<CampaignDescription, Integer> {


}
