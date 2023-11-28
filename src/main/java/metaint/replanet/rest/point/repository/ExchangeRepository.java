package metaint.replanet.rest.point.repository;

import metaint.replanet.rest.point.entity.Exchange;
import metaint.replanet.rest.point.entity.PointFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {
}
