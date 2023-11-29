package metaint.replanet.rest.auth.dto;

import metaint.replanet.rest.auth.entity.MemberRole;
import metaint.replanet.rest.auth.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String email;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String password;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String memberName;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String phone;


    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .memberName(memberName)
                .phone(phone)
                .memberRole(MemberRole.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
