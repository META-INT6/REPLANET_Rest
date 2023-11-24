package metaint.replanet.rest.pay.repository;

import metaint.replanet.rest.pay.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PayMemberRepository extends JpaRepository<Member, Long> {
    Member findByMemberCode(Long memberId);

    @Modifying
    @Query("UPDATE pay_member m SET m.currentPoint = :updatedPoint WHERE m.memberCode = :memberCode")
    void updateCurrentPointByMemberCode(@Param("updatedPoint") int updatedPoint, @Param("memberCode") Long memberCode);
}
