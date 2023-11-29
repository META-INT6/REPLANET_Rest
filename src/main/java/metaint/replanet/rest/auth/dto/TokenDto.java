package metaint.replanet.rest.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import metaint.replanet.rest.auth.entity.MemberRole;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String email;
    private String memberName;
    private MemberRole memberRole;
    private String grantType;
    private String accessToken;
    private Long tokenExpiresIn;
}