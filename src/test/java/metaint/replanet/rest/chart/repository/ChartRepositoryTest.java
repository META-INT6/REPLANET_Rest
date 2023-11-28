package metaint.replanet.rest.chart.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ChartRepositoryTest {

    @Autowired
    private ChartRepository chartRepository;

    @DisplayName("JpaRepository 인터페이스 제공 메서드 사용 테스트")
    @Test
    public void testCount() {
        //given
        int expectCount = 3;
        //when
        Long countResult = chartRepository.count();
        //then
        Assertions.assertEquals(expectCount, countResult);
    }

    @DisplayName("Distinct 쿼리 DSL 테스트")
    @Test
    public void testDistinct() {
        //when
        List<Object[]> foundCategoryList = chartRepository.findDistinctByCampaignCategory();
        //then
        Assertions.assertNotNull(foundCategoryList);
        /* foundCategoryList.forEach(row -> {
            for(Object col : row) {
                System.out.print(col);
            }
            System.out.println();
        }); */
    }

    @DisplayName("카테고리 리스트 조회 네이티브 쿼리 테스트")
    @Test
    public void testSelectCategoryOfCampaign() {
        //when
        List<Object[]> foundCategory = chartRepository.findAllCampaingCategory();
        //then
        Assertions.assertNotNull(foundCategory);
        /* foundCategory.forEach(row -> {
            for(Object col : row) {
                System.out.print(col);
            }
            System.out.println();
        }); */
    }

    @DisplayName("카테고리별 캠페인 통계 조회 네이티브 쿼리 테스트")
    @Test
    public void testSelectCampaignByCampaignCategory() {
        //when
        List<Object[]> countByCategory = chartRepository.countAndSumByCategory();
        //then
        Assertions.assertNotNull(countByCategory);
        /* countByCategory.forEach(row -> {
            for(Object col : row) {
                System.out.print(col + ":::");
            }
            System.out.println();
        });*/
    }

    @DisplayName("당해 월별 캠페인 통계 조회 네이티브 쿼리 테스트")
    @Test
    public void testSelectCampaignByCurrentyear() {
        //when
        List<Object[]> countByCurrentyear = chartRepository.countAndSumByCurrentyear();
        //then
        Assertions.assertNotNull(countByCurrentyear);
         countByCurrentyear.forEach( row -> {
            for(Object col : row) {
                System.out.print(col + ":::");
            }
            System.out.println();
        });
    }

    @DisplayName("전해 월별 캠페인 통계 조회 네이티브 쿼리 테스트")
    @Test
    public void testSelectCampaignByPreviousyear() {
        //when
        List<Object[]> countByPreviousyear = chartRepository.countAndSumByPreviousyear();
        //then
        Assertions.assertNotNull(countByPreviousyear);
        /* countByPreviousyear.forEach(row -> {
            for(Object col : row) {
                System.out.print(col + ":::");
            }
            System.out.println();
        }); */
    }

}
