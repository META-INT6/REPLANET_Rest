package metaint.replanet.rest.campaign.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@ToString
@AllArgsConstructor
@Getter
@Entity(name = "campaign")
@Table(name = "tbl_campaign_description")
public class Campaign {
    @Id
    @Column(name = "campaign_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campaignCode; // 모금 코드
    @Column(name = "campaign_title")
    private String campaignTitle; // 모금 제목
    @Column(name = "campaign_content")
    private String campaignContent; // 모금 내용
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm")
    private LocalDateTime startDate; // 모금 시작 일자
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm")
    private LocalDateTime endDate; // 모금 마감 일자
    @Column(name = "campaign_category")
    private String campaignCategory; // 모금 카테고리
    @Column(name = "current_budget")
    private int currentBudget; // 현재 모금액
    @Column(name = "goal_budget")
    private int goalBudget; // 목표 모금액
//    @Column(name = "org_name")
//    private String orgName; // 단체명
//    @Column(name = "org_description")
//    private String orgDescription; //단체 소개
//    @Column(name = "org_tel")
//    private String orgTel; // 단체 연락처
    //@OneToMany(mappedBy = "campaignCode", cascade = CascadeType.ALL)
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "campaign_code")
    //private CampaignDescFile campaignDescfile; // 파일 정보
    private List<CampaignDescFile> campaignDescfileList; // 파일 정보
    @ManyToOne
    @JoinColumn(name = "org_code")
    private Organization organization; // 기부처 코드



    protected Campaign() {}
    public Campaign campaignTitle(String val){
        this.campaignTitle = val;
        return this;
    }
    public Campaign campaignContent(String val){
        this.campaignContent = val;
        return this;
    }
    public Campaign endDate(LocalDateTime val){
        this.endDate = val;
        return this;
    }
    public Campaign campaignCategory(String val){
        this.campaignCategory = val;
        return this;
    }
    public Campaign goalBudget(int val){
        this.goalBudget = val;
        return this;
    }
//    public CampaignAndFile orgName(String val){
//        this.orgName = val;
//        return this;
//    }
//    public CampaignAndFile orgDescription(String val){
//        this.orgDescription = val;
//        return this;
//    }
//    public CampaignAndFile orgTel(String val){
//        this.orgTel = val;
//        return this;
//    }
        public Campaign campaignDescfileList(List<CampaignDescFile> val){
        this.campaignDescfileList = val;
        return this;
    }

    public Campaign builder(){
        return new Campaign(
                campaignCode,campaignTitle,campaignContent,
                startDate,endDate,campaignCategory,currentBudget,
                goalBudget,campaignDescfileList, organization);
    }
}
