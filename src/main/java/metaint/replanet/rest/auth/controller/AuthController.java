package metaint.replanet.rest.auth.controller;


import metaint.replanet.rest.auth.dto.MemberRequestDto;
import metaint.replanet.rest.auth.dto.MemberResponseDto;
import metaint.replanet.rest.auth.dto.TokenDto;

import metaint.replanet.rest.auth.jwt.CustomUserDetails;
import metaint.replanet.rest.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.signup(requestDto));
        // 여기서 ROLE_ADMIN으로 확인되면 기부처를 등록하는 signup이기 때문에 ROLE_ORG로 세팅해주는 로직을 짠다
        // HEADER에 TOKEN이 없는 경우는 비회원이 가입을 하려는 경우이기에 ROLE_USER로 한다.
    }

    @CrossOrigin(origins = "http://localhost:3000", exposedHeaders = "Authorization")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto,
                                          @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(authService.login(requestDto));
    }
    
}