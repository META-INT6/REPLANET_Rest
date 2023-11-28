package metaint.replanet.rest.org.dto;

import lombok.*;
import metaint.replanet.rest.auth.entity.MemberRole;
import metaint.replanet.rest.org.entity.Organization;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrgRequestDTO {

    private int orgCode;
    private String fileOriginName;
    private String fileSaveName;
    private String fileSavePath;
    private String fileExtension;
    private String orgDescription;

}
