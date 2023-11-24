package metaint.replanet.rest.privacy.dto;

import lombok.*;

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
    private char privacyStatus;
    private String residentNum;
}