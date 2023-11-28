package metaint.replanet.rest.privacy.entity;

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
@Entity(name = "privacyMember")
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

    @Column(name = "privacy_status")
    private char privacyStatus;

    @Column(name = "resident_num")
    private String residentNum;

    public Member privacyStatus(char val){
        this.privacyStatus = val;
        return this;
    }

    public Member residentNum(String val){
        this.residentNum = val;
        return this;
    }

    public Member builder(){
        return new Member(memberCode, memberEmail, memberName, password, phone, joinDate, memberRole, withdraw, withdrawDate, currentPoint, privacyStatus, residentNum);
    }
}