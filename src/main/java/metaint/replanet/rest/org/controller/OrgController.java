package metaint.replanet.rest.org.controller;


import metaint.replanet.rest.org.entity.Organization;
import metaint.replanet.rest.org.service.OrgService;
import metaint.replanet.rest.pay.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.privacy.dto.MemberDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin("http://localhost:3000")
@RestController
public class OrgController {

    private final OrgService orgService;

    public OrgController (OrgService orgService){
        this.orgService = orgService;
    }
    
    // 기부처 등록 (관리자 간단 정보)
    public void orgRegist(){}
    // 기부처 프로필 등록 ( 소개, 연락처, 사진 등)

    public void orgUpdateInfo(){}

    // 기부처 캠페인 등록
    public void campaignRegist(){

    }



    @GetMapping("/orgs")
    public ResponseEntity<List<Member>> getOrgs() {
        // 유효성 체크한다고 하면 현재 로그인한 놈의 ROLE_ADMIN인지 확인하는 정도 
        List<Member> orgList = orgService.getOrgList();

        return new ResponseEntity<>(orgList, HttpStatus.OK);

    }

    @GetMapping("/orgInfo/{memberCode}")
    public ResponseEntity<?> selectOrgInformation(@PathVariable int memberCode,
                                                  @RequestParam String orgPwd){

        log.info("기부처 코드 확인 : " + memberCode);
        log.info("비밃번호 넘어왔는지 확인 : " + orgPwd);
        int verify = orgService.verifyPassword(memberCode, orgPwd);
        log.info("verify는 " + verify);
        if(verify == 1){
            List<Map<String, Object>> orgInformation = orgService.selectOrgInformation(memberCode);
            log.info("정보 확인~ " + orgInformation);
            return ResponseEntity.status(HttpStatus.OK).body(orgInformation);
        } else if(verify == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 일치하지 않습니다.");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("처리 중 오류가 발생했습니다.");
    }

    @PutMapping("/orgModify/{memberCode}")
    public ResponseEntity<?> updateOrgInformation(@PathVariable int memberCode,
                                                  @RequestBody MemberDTO memberDTO,
                                                  @RequestBody String orgDescription){

        log.info("기부처 코드 확인 : " + memberCode);
        log.info("기부처 정보 넘어왔는지 확인1 : " + memberDTO);
        log.info("기부처 정보 넘어왔는지 확인2 : " + orgDescription);

        orgService.updateOrgInformation(memberDTO, orgDescription);

        return null;
    }


}
