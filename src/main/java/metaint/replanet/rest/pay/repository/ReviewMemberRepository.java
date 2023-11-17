package metaint.replanet.rest.pay.repository;

import metaint.replanet.rest.reviews.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewMemberRepository extends JpaRepository<Member, Long> {


}
