package metaint.replanet.rest.point.repository;

import metaint.replanet.rest.point.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Integer> {

    List<Exchange> findByMemberCode(int memberCode);

    List<Exchange> findByStatus(String status);
}
