package metaint.replanet.rest.campaign.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@AllArgsConstructor
@Getter
@Entity(name = "campaignDescription")
@Table(name = "tbl_campaign_description")
public class CampaignDescription {

    @Id
    @Column(name = "campaign_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int campaignCode; // 모금 코드
    @Column(name = "campaign_title")
    private String campaignTitle; // 모금 제목
    @Column(name = "campaign_content")
    private String campaignContent; // 모금 내용
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate; // 모금 시작 일자
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endDate; // 모금 마감 일자
    @Column(name = "campaign_category")
    private String campaignCategory; // 모금 카테고리
    @Column(name = "current_budget")
    private int currentBudget; // 현재 모금액
    @Column(name = "goal_budget")
    private int goalBudget; // 목표 모금액
    @Column(name = "org_name")
    private String orgName; // 단체명
    @Column(name = "org_description")
    private String orgDescription; //단체 소개
    @Column(name = "org_tel")
    private String orgTel; // 단체 연락처

//    @OneToMany(mappedBy = "charityDescription")
//    private List<CampaignDescFile> campaignDescfileList; // 파일 정보

    protected CampaignDescription() {}

    public CampaignDescription(int campaignCode) {
        this.campaignCode = campaignCode;
    }

    public CampaignDescription(String campaignTitle, String campaignContent, LocalDateTime startDate, LocalDateTime endDate, String campaignCategory, int currentBudget, int goalBudget, String orgName, String orgDescription, String orgTel) {
        this.campaignTitle = campaignTitle;
        this.campaignContent = campaignContent;
        this.startDate = startDate;
        this.endDate = endDate;
        this.campaignCategory = campaignCategory;
        this.currentBudget = currentBudget;
        this.goalBudget = goalBudget;
        this.orgName = orgName;
        this.orgDescription = orgDescription;
        this.orgTel = orgTel;
    }

    public CampaignDescription endDate(LocalDateTime val){
        this.endDate = val;
        return this;
    }

    public CampaignDescription builder(){
        return new CampaignDescription(
                campaignCode,campaignTitle,campaignContent,
                startDate,endDate,campaignCategory,currentBudget,
                goalBudget,orgName,orgDescription,orgTel);
    }
}
