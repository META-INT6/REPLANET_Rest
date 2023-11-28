package metaint.replanet.rest.org.repository;

import metaint.replanet.rest.auth.entity.MemberRole;
import metaint.replanet.rest.org.entity.Organization;
import metaint.replanet.rest.pay.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrgMemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findAllByMemberRole(MemberRole memberRole);
}
