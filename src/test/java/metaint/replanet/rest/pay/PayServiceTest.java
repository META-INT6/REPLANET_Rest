package metaint.replanet.rest.pay;

import metaint.replanet.rest.pay.entity.Pay;
import metaint.replanet.rest.pay.repository.DonationRepository;
import metaint.replanet.rest.pay.repository.PayRepository;
import metaint.replanet.rest.pay.service.PayService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureWebClient
public class PayServiceTest {

    @Autowired
    private PayService payService;

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
        Pay resultPay = payService.getPayByPayCode(payCode);
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
            payService.getPayByPayCode(payCode);
        });
    }
}
