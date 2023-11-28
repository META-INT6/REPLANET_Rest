package metaint.replanet.rest.bookmark.repository;

import metaint.replanet.rest.bookmark.entity.Bookmark;
import metaint.replanet.rest.campaign.entity.CampaignDescription;
import metaint.replanet.rest.point.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    List<Bookmark> findByMemberCodeMemberCode(int changeCode);

    int deleteByMemberCodeAndCampaignCode(Member memberCode, CampaignDescription campaignCode);
    @Modifying
    @Query(value = "DELETE FROM tbl_bookmark b WHERE b.bookmark_code in :campaignCode", nativeQuery = true)
    int deleteAllByIds(@Param("campaignCode") List<Integer> campaignCode);

}
