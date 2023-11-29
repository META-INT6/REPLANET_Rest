package metaint.replanet.rest.auth.sms.service;



import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Random;

@SpringBootTest
public class SmsServiceTest {
    @Test
    public void certifiedPhoneNumberTest() {

        String api_key = "NCSCXEQPTW0K6AEB";
        String api_secret = "O6TTAGTY5UCH84C19UUL9W6QLWSHJ4KG";
        Message coolsms = new Message(api_key, api_secret);

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", "01046668627");
        params.put("from", "01046668627");
        params.put("type", "SMS");
        params.put("text", "인증번호는" + "["+numStr+"]" + "입니다.");
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
