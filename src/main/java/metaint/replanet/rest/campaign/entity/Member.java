package metaint.replanet.rest.campaign.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import metaint.replanet.rest.auth.entity.MemberRole;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity(name = "CampaignMember")
@Table(name = "tbl_member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_code")
    private int memberCode;

    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "join_date")
    private Date joinDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role")
    private MemberRole memberRole;

    @Column(name = "withdraw")
    private boolean withdraw;

    @Column(name = "withdraw_date")
    private Date withdrawDate;

    @Column(name = "current_point")
    private int currentPoint;
}
