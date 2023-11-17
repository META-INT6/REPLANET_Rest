/*
package metaint.replanet.rest.reviews;

import metaint.replanet.rest.reviews.dto.CombineReviewDTO;
import metaint.replanet.rest.reviews.dto.ReviewDTO;
import metaint.replanet.rest.reviews.entity.Campaign;
import metaint.replanet.rest.reviews.entity.Review;
import metaint.replanet.rest.reviews.model.service.ReviewService;
import metaint.replanet.rest.reviews.repository.CampaignReviewRepository;
import metaint.replanet.rest.reviews.repository.ReviewRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper; // Import ModelMapper
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class ReviewTestApplication {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CampaignReviewRepository campaignReviewRepository;

    @Autowired
    private ModelMapper modelMapper; // Inject ModelMapper

    @DisplayName("리뷰 리스트 전체 조회 테스트")
    @Test
    public void testGetAllReviews() {
        // Given

        // When
        List<CombineReviewDTO> details = reviewService.findAllReviews();

        List<Campaign> campaignList = campaignReviewRepository.findAll();

        System.out.println("findAllReviews: " + campaignList);

        // Map the entities to DTOs using the ModelMapper
        List<CombineReviewDTO> campaignDTOList = campaignList.stream()
                .map(campaign -> modelMapper.map(campaign, CombineReviewDTO.class))
                .collect(Collectors.toList());

        // Then
        Assertions.assertNotNull(campaignDTOList);
    }

    @DisplayName("단일 리뷰 리스트 조회 테스트")
    @Test
    public void testgetSingleReview() {

        //given
        Long campaignCode = 1L;

        //when
        Campaign campaign = campaignReviewRepository.findById(campaignCode).orElseThrow(IllegalArgumentException::new);

        ReviewDTO singleReview = modelMapper.map(campaign, ReviewDTO.class);


        // Then
        Assertions.assertNotNull(singleReview);
    }

    @DisplayName("리뷰 등록 테스트")
    @Test
    public void registReviewTest() {

        //given
        ReviewDTO reviews = new ReviewDTO();

        reviews.setReviewTitle("나는 리뷰 제목이야");
        reviews.setDescription("<p>나는 상세 내용이양</p");
        reviews.setCampaignCode(32L);



        //when
        ReviewDTO singleReview = modelMapper.map(reviews, ReviewDTO.class);

        //then
        Assertions.assertEquals(reviews.getReviewTitle(), singleReview.getReviewTitle());
        Assertions.assertEquals(reviews.getDescription(), singleReview.getDescription());
        Assertions.assertEquals(reviews.getCampaignCode(), singleReview.getCampaignCode());

        System.out.println("Result : " + singleReview);
    }

    @DisplayName("검색어로 필터링해서 리뷰 가져오기")
    @Test
    public void findReviewsBySearchFilter() {

        //given
        */
/*String searchFilter = "모굼";

        //when
        List<Campaign> filteredReviewList = campaignReviewRepository.findFilteredReviews(searchFilter);

        List<CombineReviewDTO> filteredResult = filteredReviewList.stream()
                .map(filteredReviews -> modelMapper.map(filteredReviews, CombineReviewDTO.class))
                .collect(Collectors.toList());

        //then
        Assertions.assertNotNull(filteredResult);
        System.out.println("Filtered result : " + filteredResult);*//*

    }

}
*/
