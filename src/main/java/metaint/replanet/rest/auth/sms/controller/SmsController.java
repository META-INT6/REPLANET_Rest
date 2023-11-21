package metaint.replanet.rest.auth.sms.controller;

import metaint.replanet.rest.auth.sms.service.SmsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/")
public class SmsController {
    private SmsService smsService;
    String resultStr="";

    @GetMapping("/users/sms")
    public String sendSMS (@RequestParam String u_phone) {

        Random rnd  = new Random();
        StringBuffer buffer = new StringBuffer();
        for (int i=0; i<4; i++) {
            buffer.append(rnd.nextInt(10));
        }
        String cerNum = buffer.toString();
        System.out.println("수신자 번호 : " + u_phone);
        System.out.println("인증번호 : " + cerNum);
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
