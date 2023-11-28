package metaint.replanet.rest.point.dto;

import lombok.*;
import metaint.replanet.rest.auth.entity.MemberRole;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberDTO {

    private int memberCode;
    private String memberId;
    private String memberName;
    private String password;
    private String phone;
    private Date joinDate;
    private String memberRole;
    private char withdraw;
    private Date withdrawDate;
    private int currentPoint;
}
