package metaint.replanet.rest.pay.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.auth.jwt.TokenProvider;
import metaint.replanet.rest.campaign.repository.CampaignRepository;
import metaint.replanet.rest.pay.dto.pay.DonationAmountDTO;
import metaint.replanet.rest.pay.dto.pay.KakaoPayApprovalVO;
import metaint.replanet.rest.pay.dto.pay.KakaoPayReadyVO;
import metaint.replanet.rest.pay.entity.CampaignDescription;
import metaint.replanet.rest.pay.entity.Donation;
import metaint.replanet.rest.pay.entity.Member;
import metaint.replanet.rest.pay.entity.Pay;
import metaint.replanet.rest.pay.repository.DonationRepository;
import metaint.replanet.rest.pay.repository.PayCampaignRepository;
import metaint.replanet.rest.pay.repository.PayMemberRepository;
import metaint.replanet.rest.pay.repository.PayRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;


@Service
@Slf4j
public class PayService {

    private final DonationRepository donationRepository;
    private final PayRepository payRepository;
    private final PayCampaignRepository payCampaignRepository;
    private final PayMemberRepository payMemberRepository;

    private final TokenProvider tokenProvider;

    public PayService(DonationRepository donationRepository, PayRepository payRepository, CampaignRepository campaignRepository, PayCampaignRepository payCampaignRepository, PayMemberRepository payMemberRepository, TokenProvider tokenProvider) {
        this.donationRepository = donationRepository;
        this.payRepository = payRepository;
        this.payCampaignRepository = payCampaignRepository;
        this.payMemberRepository = payMemberRepository;
        this.tokenProvider = tokenProvider;
    }
    private static final String HOST = "https://kapi.kakao.com";
    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;


    public String kakaoPayReady(DonationAmountDTO amount, String campaignCode, String memberCode) {
        log.info("[kakaoPayReady()] ---------------------------------------- ");
        log.info("[kakaoPayReady() amount.getPointAmount()] : " + amount.getPointAmount());
        log.info("[kakaoPayReady() amount.campaignCode] : " + campaignCode);

        String memberId = null;
        Optional<Member> optionalMember = payMemberRepository.findById(Long.parseLong(memberCode));
        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();
            memberId = member.getMemberEmail();
            log.info("kakaoPayReady() memberId" + memberId);
        } else {
            log.info("kakaoPayReady() 멤버 정보가 없습니다!");
        }

        Optional<CampaignDescription> campaignDescriptions = payCampaignRepository.findById(Integer.valueOf(campaignCode));

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "77683905e97c497707a05671168be68f");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME"); // 가맹점코드 테스트에선 고정
        params.add("partner_order_id", String.valueOf(campaignDescriptions.get().getCampaignCode())); // orderId
        params.add("partner_user_id", memberId); // userId
        params.add("item_name", campaignDescriptions.get().getCampaignTitle()); // 상품명
        params.add("quantity", "1"); // 수량
        params.add("total_amount", String.valueOf(amount.getCashAmount())); // 현금결제액
        params.add("tax_free_amount", "100"); // 상품 비과세 금액
        params.add("approval_url", "http://localhost:8001/kakaoPaySuccess?pointAmount=" + amount.getPointAmount() + "&campaignCode=" + campaignCode + "&memberCode=" + memberCode);
        params.add("cancel_url", "http://localhost:8001/kakaoPayCancle?campaignCode=" + campaignCode);
        params.add("fail_url", "http://localhost:8001/kakaoPaySuccessFail?campaignCode=" + campaignCode);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);

            log.info("[kakaoPayReady() kakaoPayReadyVO] : " + kakaoPayReadyVO);

            return kakaoPayReadyVO.getNext_redirect_pc_url();
            // 기부하기 누르는 순간 얘들은 이미 다실행되고 카카오페이 결제페이지임!
            // 걍 다른 카카오페이API 영역에서 놀고있는거임

        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "/donations";
    }

    @Transactional
    public KakaoPayApprovalVO kakaoPayInfo(String pg_token, String pointAmount, String campaignCode, String memberCode) {

        log.info("[kakaoPayInfo()]............................................");

        Member refMember = null;
        Optional<Member> optionalMember = payMemberRepository.findById(Long.parseLong(memberCode));
        if (optionalMember.isPresent()) {
            refMember = optionalMember.get();
            log.info("kakaoPayReady() refMember" + refMember);
        } else {
            refMember = null;
            log.info("kakaoPayReady() 멤버 정보가 없습니다!");
        }

        Optional<CampaignDescription> campaignDescriptions = payCampaignRepository.findById(Integer.valueOf(campaignCode));

        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "77683905e97c497707a05671168be68f");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME"); // 가맹점 코드 -> 테스트용 고정!
        params.add("tid", kakaoPayReadyVO.getTid()); // 결제 고유번호 -> 결제 준비 API 응답에 포함돼서 온거임 -> VO를 통해서 받아옴
        params.add("partner_order_id", String.valueOf(campaignDescriptions.get().getCampaignCode())); // 현재 선택한 캠페인
        params.add("partner_user_id", refMember.getMemberEmail()); // 현재 로그인한 사용자!
        params.add("pg_token", pg_token);

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);

            if (kakaoPayApprovalVO != null && kakaoPayApprovalVO.getApproved_at() != null) {
                // 필요한 정보 추출
                String paymentCode = kakaoPayApprovalVO.getTid();
                Date date = kakaoPayApprovalVO.getApproved_at();
                Instant instant = date.toInstant();
                ZoneId zoneId = ZoneId.systemDefault(); // 또는 다른 시간대로 설정
                LocalDateTime donationDateTime = instant.atZone(zoneId).toLocalDateTime();

                // Donation 엔티티 생성
                Donation newDonation = Donation.builder()
                                                .donationDateTime(donationDateTime)
                                                .donationPoint(Integer.parseInt(pointAmount))
                                                .refMember(refMember)  // Member 엔티티 객체
                                                .refCampaign(campaignDescriptions.get())  // CampaignDescription 엔티티 객체
                                                .build();

                // builder() 통해서 값 넣기
                Pay newPay = Pay.builder()
                                .payAmount(kakaoPayApprovalVO.getAmount().getTotal())
                                .payTid(kakaoPayApprovalVO.getTid())
                                .refDonation(newDonation)
                                .build();

                donationRepository.save(newDonation); // 기부 내역 테이블에 값 저장
                payRepository.save(newPay); // 결제 내역 테이블에 값 저장
                payCampaignRepository.updateCurrentBudget(newDonation.getDonationPoint(), newPay.getPayAmount(), newDonation.getRefCampaign().getCampaignCode()); // 캠페인 테이블에 currentBudget 업데이트

                int updatedPoint = refMember.getCurrentPoint() - Integer.parseInt(pointAmount);
                payMemberRepository.updateCurrentPointByMemberCode(updatedPoint, refMember.getMemberCode());
                log.info("postPointDonation() updatedPoint : " + updatedPoint);

                kakaoPayApprovalVO.setPayCode(newPay.getPayCode());
                log.info("[kakaoPayInfo() kakaoPayApprovalVO.payCode] : " + kakaoPayApprovalVO.getPayCode());
            }

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

    public List<Pay> getPaysByMember(String memberCode) {
        log.info("[getPays()] -----------------------------------");

        Long memberId = Long.parseLong(memberCode);

        Member member = payMemberRepository.findByMemberCode(memberId);

        List<Pay> payList = payRepository.findByRefDonationRefMember(member);

        log.info("[getDonation() payList] : " + payList);

        return payList;
    }

    public List<Pay> getPaysByDateRange(String startDate, String endDate, String memberCode) {
        log.info("[getPaysByDateRange()] -----------------------------------");

        LocalDateTime stardDateTime = LocalDateTime.parse(startDate + "T00:00:00");
        LocalDateTime endDateTime = LocalDateTime.parse(endDate + "T23:59:59");

        Optional<Member> optionalMember = Optional.ofNullable(payMemberRepository.findByMemberCode(Long.parseLong(memberCode)));
        if(!optionalMember.isPresent()) {
            log.info("[getPaysByDateRange()] 멤버를 찾을 수 없습니다 해당 멤버 코드 : " + memberCode);
            return Collections.emptyList();
        }

        Member member = optionalMember.get();

        List<Donation> donations = donationRepository.findAllByRefMemberAndDonationDateTimeBetween(member, stardDateTime, endDateTime);

        List<Pay> pays = new ArrayList<>();
        for (Donation donation : donations) {
            Pay pay = payRepository.findByRefDonation_DonationCode(donation.getDonationCode());
            pays.add(pay);
        }
        log.info("[getPaysByDateRange() pays] : " + pays);

        log.info("[getPaysByDateRange()] -----------------------------------");
        return pays;
    }

    public Pay getPayByPayCode(String payCode) {
        log.info("[getPayByPayCode(String payCode)] -----------------------------------");
        // 결제 직후 기부상세내용 보여주는 놈 -> payCode 통해서 해당 Pay 조회

        Pay pay = payRepository.findByPayCode(Integer.parseInt(payCode));

        if (pay != null) {
            return pay;
        } else {
            log.error("payCode에 해당하는 Pay를 찾을수없으요 payCode : " + payCode);
            throw new EntityNotFoundException("payCode에 해당하는 Pay를 찾을수없으요 payCode : " + payCode);
        }
    }

    public Member getPointByMember(String memberCode) {
        log.info("[getPointByMember(String memberCode)] -----------------------------------");
        // 결제페이지에서 가용 포인트를 보여주기위한 메소드

        Member member = payMemberRepository.findByMemberCode(Long.valueOf(memberCode));

        if (member != null) {
            return member;
        } else {
            log.error("memberCode에 해당하는 Member를 찾을수없으요 memberCode : " + memberCode);
            throw new EntityNotFoundException("memberCode에 해당하는 Member를 찾을수없으요 payTid : " + memberCode);
        }
    }

    @Transactional
    public int postPointDonation(DonationAmountDTO amount, String campaignCode, String memberCode) {
        log.info("[postPointDonation(DonationAmountDTO amount)] -----------------------------------");

        Member refMember;
        Optional<Member> optionalMember = payMemberRepository.findById(Long.parseLong(memberCode));
        if (optionalMember.isPresent()) {
            refMember = optionalMember.get();
            log.info("kakaoPayReady() refMember" + refMember);
        } else {
            refMember = null;
            log.info("kakaoPayReady() 멤버 정보가 없습니다!");
        }

        Optional<CampaignDescription> campaignDescriptions = payCampaignRepository.findById(Integer.valueOf(campaignCode));

        Optional<Pay> newPay = Optional.ofNullable(campaignDescriptions.map(campaignDescription -> {
            log.info("[postPointDonation() campaignDescriptions] : " + campaignDescription);

            CampaignDescription campaign = campaignDescription;

            Donation newDonation = Donation.builder()
                    .donationDateTime(LocalDateTime.now())
                    .donationPoint(amount.getPointAmount())
                    .refMember(refMember)
                    .refCampaign(campaign)
                    .build();

            Pay pay = Pay.builder()
                    .payAmount(amount.getCashAmount())
                    .payTid(null)
                    .refDonation(newDonation)
                    .build();


            donationRepository.save(newDonation);
            payRepository.save(pay);
            payCampaignRepository.updateCurrentBudget(newDonation.getDonationPoint(), 0, newDonation.getRefCampaign().getCampaignCode());

            int updatedPoint = refMember.getCurrentPoint() - amount.getPointAmount();
            payMemberRepository.updateCurrentPointByMemberCode(updatedPoint, refMember.getMemberCode());
            log.info("postPointDonation() updatedPoint : " + updatedPoint);

            return pay;
        }).orElseThrow(() -> new RuntimeException("해당하는 캠페인이 없습니다!")));

        return newPay.get().getPayCode();
    }

}
