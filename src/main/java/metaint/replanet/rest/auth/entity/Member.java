package metaint.replanet.rest.auth.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@DynamicInsert
@DynamicUpdate
@Getter
@NoArgsConstructor
@Table(name = "tbl_member")
@Entity
public class Member {

    @Id
    @Column(name = "member_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberCode;

    @NotNull
    @Column(name = "member_email")
    private String email;

    @NotNull
    @Column(name = "member_name")
    private String memberName;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "join_date")
    private Date joinDate;

    @Column(name = "member_role")
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @NotNull
    @ColumnDefault("N")
    @Column(name = "withdraw")
    private String withdraw;

    @ColumnDefault("null")
    @Column(name = "withdraw_date")
    private Date withdrawDate;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "current_point")
    private int currentPoint;

    @NotNull
    @ColumnDefault("replanet")
    @Column(name = "provider")
    private String provider;

    @ColumnDefault("null")
    @Column(name = "provider_id")
    private String providerId;

    @Builder
    public Member(Long memberCode, String email, String memberName, String password, String phone, Date joinDate, MemberRole memberRole, String withdraw, Date withdrawDate, int currentPoint,
                  String provider, String providerId) {
        this.memberCode = memberCode;
        this.email = email;
        this.memberName = memberName;
        this.password = password;
        this.phone = phone;
        this.joinDate = joinDate;
        this.memberRole = memberRole;
        this.withdraw = withdraw;
        this.withdrawDate = withdrawDate;
        this.currentPoint = currentPoint;
        this.provider = provider;
        this.providerId = providerId;
    }

    public Member(String email, String memberName, MemberRole memberRole) {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
