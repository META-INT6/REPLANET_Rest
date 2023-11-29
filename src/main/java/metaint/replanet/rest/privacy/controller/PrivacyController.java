package metaint.replanet.rest.privacy.controller;

import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.privacy.dto.MemberDTO;
import metaint.replanet.rest.privacy.service.PrivacyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class PrivacyController {

    private PrivacyService privacyService;

    public PrivacyController(PrivacyService privacyService){
        this.privacyService = privacyService;
    }

    @PutMapping("provideInfo")
    public ResponseEntity<?> updateMemberPrivacy(@RequestBody MemberDTO memberDTO){

        int result = 0;
        log.info("memberDTO 확인 : " + memberDTO);
        log.info("privacyStatus 확인 : " + memberDTO.getPrivacyStatus());
        if(memberDTO.getPrivacyStatus() == 'Y'){
            result = privacyService.updateMemberPrivacy(memberDTO);
            if(result == 1){
                return ResponseEntity.status(HttpStatus.OK).body("동의 처리 완료");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("처리 중 서버 오류 발생");
            }
        } else if (memberDTO.getPrivacyStatus() == 'N'){
            result = privacyService.withdrawMemberPrivacy(memberDTO);
            if(result == 1){
                return ResponseEntity.status(HttpStatus.OK).body("철회 처리 완료");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("처리 중 서버 오류 발생");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("신청 처리 중 오류 발생");
    }
}
