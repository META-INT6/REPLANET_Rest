package metaint.replanet.rest.auth.mail.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
    private String email;
    private String title;
    private String message;


}
