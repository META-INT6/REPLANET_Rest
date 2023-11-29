package metaint.replanet.rest.chart.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "chartMember")
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
    private LocalDateTime joinDate;
    @Column(name = "member_role")
    private String memberRole;
    @Column(name = "withdraw")
    private boolean withdraw;
    @Column(name = "withdraw_date")
    private LocalDateTime withdrawDate;
    @Column(name = "current_point")
    private int currentPoint;

    protected Member() {}

    public Member(int memberCode, String memberEmail, String memberName, String password, String phone, LocalDateTime joinDate, String memberRole, boolean withdraw, LocalDateTime withdrawDate, int currentPoint) {
        this.memberCode = memberCode;
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.password = password;
        this.phone = phone;
        this.joinDate = joinDate;
        this.memberRole = memberRole;
        this.withdraw = withdraw;
        this.withdrawDate = withdrawDate;
        this.currentPoint = currentPoint;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public boolean isWithdraw() {
        return withdraw;
    }

    public LocalDateTime getWithdrawDate() {
        return withdrawDate;
    }

    public int getCurrentPoint() {
        return currentPoint;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberCode=" + memberCode +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberName='" + memberName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", joinDate=" + joinDate +
                ", memberRole='" + memberRole + '\'' +
                ", withdraw=" + withdraw +
                ", withdrawDate=" + withdrawDate +
                ", currentPoint=" + currentPoint +
                '}';
    }
}
