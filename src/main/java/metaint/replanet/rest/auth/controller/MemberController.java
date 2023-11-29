package metaint.replanet.rest.auth.controller;

import metaint.replanet.rest.auth.dto.ChangePasswordRequestDto;
import metaint.replanet.rest.auth.dto.MemberResponseDto;
import metaint.replanet.rest.auth.service.MemberService;
import metaint.replanet.rest.auth.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/password")
    public ResponseEntity<MemberResponseDto> setMemberPassword(@RequestBody ChangePasswordRequestDto request) {
        return ResponseEntity.ok(memberService.changeMemberPassword(request.getExPassword(), request.getNewPassword()));
    }


    @GetMapping("/emailcheck")
    public ResponseEntity<?> checkEmailDuplication(@RequestParam(value = "member_email") String email) throws Exception {
        System.out.println(email);

        if (memberService.existsByEmail(email) == true) {
            throw new Exception("이미 사용중인 이메일입니다.");
        } else {
            return ResponseEntity.ok("사용 가능한 이메일입니다.");
        }
    }

}