package metaint.replanet.rest.auth.sms.service;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SmsService {
    public void smsService(String u_phone, String cerNum) {
        String api_key = "NCSCXEQPTW0K6AEB";
        String api_secret = "O6TTAGTY5UCH84C19UUL9W6QLWSHJ4KG";
        Message coolsms = new Message(api_key, api_secret);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", u_phone); // 수신전화번호
        params.put("from", "01046668627"); // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "[BitMovie] 문자 본인인증 서비스 : 인증번호는 " + "[" + cerNum + "]" + " 입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
