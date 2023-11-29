package metaint.replanet.rest.chart.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class DonationChartRepositoryTest {

    @Autowired
    private DonationChartRepository donationChartRepository;

    @DisplayName("시간별 사용포인트와 결제금액 조회 네이티브 쿼리 테스트")
    @Test
    public void testSelectDonationByTime() {
        //when
        List<Object[]> foundDonationByTime = donationChartRepository.donateByTime();

        //then
        Assertions.assertNotNull(foundDonationByTime);
        foundDonationByTime.forEach(row -> {
            for(Object col : row) {
                System.out.print(col + ":::");
            }
            System.out.println();
        });
    }
}
