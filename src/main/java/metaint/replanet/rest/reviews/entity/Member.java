package metaint.replanet.rest.reviews.entity;

import lombok.*;
import metaint.replanet.rest.auth.entity.MemberRole;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "review_member")
@Table(name = "tbl_member")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(toBuilder = true)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_code")
    private Long memberCode;
    @Column(name = "member_id")
    private String memberId;
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
}

