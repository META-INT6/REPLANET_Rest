package metaint.replanet.rest.reviews;

import metaint.replanet.rest.reviews.dto.ReviewDTO;
import metaint.replanet.rest.reviews.entity.Review;
import metaint.replanet.rest.reviews.model.service.ReviewService;
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
    private ModelMapper modelMapper; // Inject ModelMapper

    @DisplayName("리뷰 리스트 전체 조회 테스트")
    @Test
    public void testGetAllReviews() {
        // Given

        // When
        List<ReviewDTO> details = reviewService.findAllReviews();

        List<Review> reviewList = reviewRepository.findAll();

        System.out.println("findAllReviews: " + reviewList);

        // Map the entities to DTOs using the ModelMapper
        List<ReviewDTO> reviewDTOList = reviewList.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());

        // Then
        Assertions.assertNotNull(reviewDTOList);
    }

    @DisplayName("단일 리뷰 리스트 조회 테스트")
    @Test
    public void testgetSingleReview() {

        //given
        Long campaignRevCode = 1L;

        //when
        Review review = reviewRepository.findById(campaignRevCode).orElseThrow(IllegalArgumentException::new);

        ReviewDTO singleReview = modelMapper.map(review, ReviewDTO.class);


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
        reviews.setCampaignCampaignCode(32L);
        reviews.setCampaignRevCode(1L);


        //when
        ReviewDTO singleReview = modelMapper.map(reviews, ReviewDTO.class);

        //then
        Assertions.assertEquals(reviews.getReviewTitle(), singleReview.getReviewTitle());
        Assertions.assertEquals(reviews.getDescription(), singleReview.getDescription());
        Assertions.assertEquals(reviews.getCampaignCampaignCode(), singleReview.getCampaignCampaignCode());

        System.out.println("Result : " + singleReview);
    }
}
