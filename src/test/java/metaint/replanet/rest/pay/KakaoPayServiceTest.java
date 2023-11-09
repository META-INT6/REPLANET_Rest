package metaint.replanet.rest.pay;

import metaint.replanet.rest.pay.entity.CampaignDescription;
import metaint.replanet.rest.pay.entity.Donation;
import metaint.replanet.rest.pay.entity.Member;
import metaint.replanet.rest.pay.entity.Pay;
import metaint.replanet.rest.pay.repository.DonationRepository;
import metaint.replanet.rest.pay.repository.PayRepository;
import metaint.replanet.rest.pay.service.KakaoPayService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWebClient
public class KakaoPayServiceTest {

    @Autowired
    private KakaoPayService kakaoPayService;

    @MockBean
    private PayRepository payRepository;

    @MockBean
    private DonationRepository donationRepository;

    @DisplayName("임의의 payCode를 통해서 Pay를 조회하는 메소드 테스트")
    @Test
    public void testGetPayByPayCode() {
        // given
        String payCode = "123";

        // when
        Pay mockPay = Pay.builder().payCode(123).build();

        when(payRepository.findByPayCode(123)).thenReturn(mockPay);

        // then
        Pay resultPay = kakaoPayService.getPayByPayCode(payCode);
        assertThat(resultPay).isEqualTo(mockPay);
    }

    @DisplayName("존재하지 않는 payCode 값을 넣으면 null 반환하는지 테스트")
    @Test
    public void testGetPayByPayCodeNotFound() {
        // given
        String payCode = "999";
        // when
        when(payRepository.findByPayCode(999)).thenReturn(null);

        // then
        assertThrows(EntityNotFoundException.class, () -> {
            kakaoPayService.getPayByPayCode(payCode);
        });
    }
}
