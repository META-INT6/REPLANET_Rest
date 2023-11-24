package metaint.replanet.rest.auth.sms.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SmsService {
    public void smsService(String u_phone, String cerNum) throws JsonProcessingException {
        String api_key = "NCSCXEQPTW0K6AEB";
        String api_secret = "O6TTAGTY5UCH84C19UUL9W6QLWSHJ4KG";
        Message coolsms = new Message(api_key, api_secret);

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> phoneMap = objectMapper.readValue(u_phone, Map.class);
        String phone = phoneMap.get("u_phone");

        log.info("[smsService() phone] : " + phone);

        // 4 params(to, from, type, text) are mandatory. must be filled
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phone); // 수신전화번호
        params.put("from", "01046668627"); // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
        params.put("type", "SMS");
        params.put("text", "인증번호는" + "["+cerNum+"]" + "입니다.");
        params.put("app_version", "test app 1.2"); // application name and version

        log.info("[smsService() params] : " + params);

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}