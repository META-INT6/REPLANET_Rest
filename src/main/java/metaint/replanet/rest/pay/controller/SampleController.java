package metaint.replanet.rest.pay.controller;

//import lombok.extern.slf4j.Slf4j;
import metaint.replanet.rest.pay.dto.DonationDataDTO;
import metaint.replanet.rest.pay.service.KakaoPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import lombok.Setter;
import org.springframework.web.servlet.ModelAndView;

//@Slf4j
@RestController
@CrossOrigin
public class SampleController {

//    @Setter(onMethod_ = @Autowired)
    private KakaoPay kakaopay;


    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {
        System.out.println("GetMapping /kakaoPay ㅎㅇㅎㅇ");
    }

    @PostMapping("/kakaoPay")
    public String kakaoPay(@RequestBody DonationDataDTO donation) {
//        log.info("[POST /kakaoPay] -------------------------------------");
//        log.info("[cashAmount] : " + donation.getCashAmount());
//        log.info("[pointAmount] : " + donation.getPointAmount());
//        log.info("[finalAmount] : " + donation.getFinalAmount());

        return "redirect:" + kakaopay.kakaoPayReady(donation);
    }

    @GetMapping("/kakaoPaySuccess")
    public ModelAndView kakaoPaySuccess(@RequestParam("pg_token") String pg_token,
                                        ModelAndView mv) {
//        log.info("[GET /kakaoPaySuccess]-------------------------------------");
//        log.info("kakaoPaySuccess pg_token : " + pg_token);

        mv.addObject("info", kakaopay.kakaoPayInfo(pg_token));
        mv.setViewName("redirect:http://localhost:3000/donations/success");
        return mv;
    }

    @GetMapping("/kakaoPayCancle")
    public void kakaoPayCancel() {
//        log.info("[GET /kakaoPayCancle]-------------------------------------");
        System.out.println("kakaoPayCancle 취소하면 뭐할지 생각 ㄱ");
    }

    @GetMapping("/kakaoPaySuccessFail")
    public void kakaoPaySuccessFail() {
//        log.info("[GET /kakaoPaySuccessFail]-------------------------------------");
        System.out.println("kakaoPaySuccessFail 결제 실패요!");
    }

}
