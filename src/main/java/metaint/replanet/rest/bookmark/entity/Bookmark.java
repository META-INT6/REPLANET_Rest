package metaint.replanet.rest.bookmark.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import metaint.replanet.rest.campaign.entity.CampaignDescription;
import metaint.replanet.rest.point.entity.Member;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "bookmark")
@Table(name = "tbl_bookmark")
public class Bookmark {
    @Id
    @Column(name = "bookmark_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookmarkCode;
    @ManyToOne
    @JoinColumn(name = "member_code")
    private Member memberCode;
    @ManyToOne
    @JoinColumn(name = "campaign_code")
    private CampaignDescription campaignCode;

    public Bookmark memberCode(Member val){
        this.memberCode = val;
        return this;
    }
    public Bookmark campaignCode(CampaignDescription val){
        this.campaignCode = val;
        return this;
    }

    public Bookmark builder(){
        return new Bookmark(
                bookmarkCode,memberCode,campaignCode
        );
    }
}
