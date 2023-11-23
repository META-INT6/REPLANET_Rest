package metaint.replanet.rest.privacy.controller;

import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.privacy.dto.MemberDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class PrivacyController {


    @PutMapping("provideInfo")
    public ResponseEntity<?> updateMemberPrivacy(@RequestBody MemberDTO memberDTO){

        int result = 0;
        log.info("memberDTO 확인 : " + memberDTO);

        return null;
    }
}
