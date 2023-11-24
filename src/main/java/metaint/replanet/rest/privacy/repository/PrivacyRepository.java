package metaint.replanet.rest.privacy.repository;

import metaint.replanet.rest.privacy.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivacyRepository extends JpaRepository<Member, Integer> {
}
