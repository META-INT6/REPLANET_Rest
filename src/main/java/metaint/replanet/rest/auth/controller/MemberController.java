package metaint.replanet.rest.auth.controller;

import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.auth.dto.ChangePasswordRequestDto;
import metaint.replanet.rest.auth.dto.MemberRequestDto;
import metaint.replanet.rest.auth.dto.MemberResponseDto;
import metaint.replanet.rest.auth.service.MemberService;
import metaint.replanet.rest.auth.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/password")
    public ResponseEntity<MemberResponseDto> setMemberPassword(@RequestBody ChangePasswordRequestDto request) {
        return ResponseEntity.ok(memberService.changeMemberPassword(request.getExPassword(), request.getNewPassword()));
    }


    @PostMapping("/emailcheck/{email}")
    public ResponseEntity<?> checkEmailDuplication(@PathVariable String email) throws Exception {
        System.out.println(email);

        if (memberService.existsByEmail(email) == true) {
            log.info(email + "은(는) 이미 사용 중인 이메일입니다.");
            throw new Exception("이미 사용 중인 이메일입니다.");
        } else {
            return ResponseEntity.ok(email + "은(는) 사용 가능한 이메일입니다.");
        }
    }

}