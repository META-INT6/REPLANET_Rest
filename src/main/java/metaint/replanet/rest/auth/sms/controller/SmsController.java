package metaint.replanet.rest.auth.sms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.auth.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/")
public class SmsController {
    @Autowired
    private SmsService smsService;
    String resultStr="";

    @PostMapping("/users/sms")
    public String sendSMS (@RequestBody String u_phone) throws JsonProcessingException {

        Random rnd  = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i=0; i<4; i++) {
            buffer.append(rnd.nextInt(10));
        }
        String cerNum = buffer.toString();
        log.info("[/users/sms 수신자 번호 u_phone] : " + u_phone);
        log.info("[/users/sms 인증 번호 cerNum] : " + cerNum);
        smsService.smsService(u_phone, cerNum);
        return cerNum;
    }

//    @GetMapping("/check/sendSMS")
//    public @ResponseBody
//    String sendSMS(String phoneNumber) {
//
//        Random rand  = new Random();
//        String numStr = "";
//        for(int i=0; i<4; i++) {
//            String ran = Integer.toString(rand.nextInt(10));
//            numStr+=ran;
//        }
//
//        System.out.println("수신자 번호 : " + phoneNumber);
//        System.out.println("인증번호 : " + numStr);
//        smsService.certifiedPhoneNumber(phoneNumber,numStr);
//        return numStr;
//    }
}