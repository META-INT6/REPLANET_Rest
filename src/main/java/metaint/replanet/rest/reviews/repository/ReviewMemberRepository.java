package metaint.replanet.rest.reviews.repository;

import metaint.replanet.rest.reviews.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewMemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m.memberEmail FROM review_member m WHERE m.memberCode = :memberCode")
    String findEmailByMemberCode(@Param("memberCode") Long memberCode);
}
