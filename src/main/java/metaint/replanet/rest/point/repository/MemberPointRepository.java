package metaint.replanet.rest.point.repository;

import metaint.replanet.rest.point.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPointRepository extends JpaRepository<Member, Integer> {
}
