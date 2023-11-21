package metaint.replanet.rest.bookmark.repository;

import metaint.replanet.rest.bookmark.entity.Bookmark;
import metaint.replanet.rest.campaign.entity.CampaignDescription;
import metaint.replanet.rest.point.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    List<Bookmark> findByMemberCodeMemberCode(int changeCode);

    int deleteByMemberCodeAndCampaignCode(Member memberCode, CampaignDescription campaignCode);
}
