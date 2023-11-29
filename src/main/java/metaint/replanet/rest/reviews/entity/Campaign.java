package metaint.replanet.rest.reviews.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity(name = "reviewPkg_entityCampaign")
@Table(name = "tbl_campaign_description")
@NoArgsConstructor
@Getter
@ToString
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campaign_code")
    private Long campaignCode;

    @Column(name = "campaign_title")
    private String campaignTitle;

    @Column(name = "campaign_content")
    private String campaignContent;

    @Column(name = "start_date")
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm")
    private LocalDateTime endDate;

    @Column(name = "campaign_category")
    private String campaignCategory;

    @Column(name = "current_budget")
    private Integer currentBudget;

    @Column(name = "goal_budget")
    private Integer goalBudget;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "org_description")
    private String orgDescription;

    @Column(name = "org_tel")
    private String orgTel;

    @OneToOne(mappedBy = "campaign", optional = true, fetch = FetchType.LAZY)
    private Review review;
}
