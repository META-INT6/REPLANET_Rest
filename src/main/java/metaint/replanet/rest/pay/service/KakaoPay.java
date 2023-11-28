package metaint.replanet.rest.pay.service;

import java.net.URI;
import java.net.URISyntaxException;

//import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.pay.dto.DonationDataDTO;
import metaint.replanet.rest.pay.dto.KakaoPayApprovalVO;
import metaint.replanet.rest.pay.dto.KakaoPayReadyVO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Service
//@Slf4j
public class KakaoPay {

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    public String kakaoPayReady(DonationDataDTO donation) {
//        log.info("[kakaoPayReady()] ---------------------------------------- ");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "77683905e97c497707a05671168be68f");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME"); // 가맹점코드 테스트에선 고정
        params.add("partner_order_id", "1"); // orderId
        params.add("partner_user_id", "기부왕"); // userId
        params.add("item_name", "산불구호대작전"); // 상품명
        params.add("quantity", "1"); // 수량
//        params.add("total_amount", String.valueOf(donation.getFinalAmount())); // 총액
        params.add("tax_free_amount", "100"); // 상품 비과세 금액
        params.add("approval_url", "http://localhost:8001/kakaoPaySuccess");
        params.add("cancel_url", "http://localhost:8001/kakaoPayCancle");
        params.add("fail_url", "http://localhost:8001/kakaoPaySuccessFail");
        // 성공, 취소, 실패 페이지 만들어야함!

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);

//            log.info("[kakaoPayReadyVO] : " + kakaoPayReadyVO);

//            return kakaoPayReadyVO.getNext_redirect_pc_url();
            // 결제 완료하면 여기서 끝나서 바로 SampleController로 감.

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "/donations";
    }

    public KakaoPayApprovalVO kakaoPayInfo(String pg_token) {

//        log.info("[kakaoPayInfo()]............................................");

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "77683905e97c497707a05671168be68f");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME"); // 가맹점 코드 -> 테스트용 고정!
//        params.add("tid", kakaoPayReadyVO.getTid()); // 결제 고유번호 -> 결제 준비 API 응답에 포함돼서 온거임 -> VO를 통해서 받아옴
        params.add("partner_order_id", "1"); // order_id 이건 어디서 들고와야하지?? -> 캠페인번호!! -> 뭐 dto나 엔티티 통해서 들고오던가 해야지 -> 현재 선택한 캠페인
        params.add("partner_user_id", "기부왕"); // 얘도 동일하게 -> 뭐 dto나 엔티티 통해서 들고오던가 해야지 -> 현재 로그인한 사용자!
        params.add("pg_token", pg_token);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
//            log.info("" + kakaoPayApprovalVO);

            return kakaoPayApprovalVO;

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
