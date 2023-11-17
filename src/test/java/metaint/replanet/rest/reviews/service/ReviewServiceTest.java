/*
package metaint.replanet.rest.reviews.service;

import metaint.replanet.rest.reviews.dto.CommentDTO;
import metaint.replanet.rest.reviews.dto.ReviewDTO;
import metaint.replanet.rest.reviews.dto.ReviewFileDTO;
import metaint.replanet.rest.reviews.entity.Campaign;
import metaint.replanet.rest.reviews.entity.Comment;
import metaint.replanet.rest.reviews.entity.Review;
import metaint.replanet.rest.reviews.entity.ReviewFile;
import metaint.replanet.rest.reviews.model.service.ReviewService;
import metaint.replanet.rest.reviews.repository.CampaignReviewRepository;
import metaint.replanet.rest.reviews.repository.ReviewCommentRepository;
import metaint.replanet.rest.reviews.repository.ReviewFileRepository;
import metaint.replanet.rest.reviews.repository.ReviewRepository;
import metaint.replanet.rest.util.FileUploadUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CampaignReviewRepository campaignReviewRepository;

    @Autowired
    private ReviewFileRepository reviewFileRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewCommentRepository reviewCommentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ReviewServiceTest(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @DisplayName("캠페인 전체 조회 테스트")
    @Test
    public void findAllReviews() {

        //given

        //when
        List<Campaign> reviewList = campaignReviewRepository.findAll();

        //then
        Assertions.assertNotNull(reviewList);
    }


    @DisplayName("리뷰 단일 조회 테스트")
    @Test
    public void findSpecificReviewByCampaignCode() {
        //given
        Long campaignCode = 21L;

        //when
        Optional<Campaign> campaign = campaignReviewRepository.findById(campaignCode);

        //then
        Assertions.assertNotNull(campaign);
    }


    @DisplayName("리뷰 신규 등록 테스트")
    @Test
    public void registNewReview() throws IOException {
        //given
        String reviewTitle = "리뷰 제목 1 입니다";
        String description = "리뷰 상세 내용 1 입니다";
        Long campaignCode = 21L;

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewTitle(reviewTitle);
        reviewDTO.setDescription(description);
        reviewDTO.setCampaignCode(campaignCode);

        MockMultipartFile imageFile = new MockMultipartFile(
                "file",
                "image.png",
                "image/png",
                "Some image data".getBytes()
        );

        Long reviewCode = reviewRepository.findByCampaignCode(reviewDTO.getCampaignCode());

        // When
        Review insertReview = modelMapper.map(reviewDTO, Review.class);
        reviewRepository.save(insertReview);

        ReviewFileDTO reviewFileDTO = new ReviewFileDTO();

        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        int result = 0;

        replaceFileName = FileUploadUtils.saveFile("somePath", imageName, imageFile);


        reviewFileDTO.setFileSaveName(replaceFileName);
        reviewFileDTO.setFileSavePath("http://localhost:3000" + "somePath" + "/" + imageFile.getOriginalFilename());
        reviewFileDTO.setFileExtension("PNG");
        reviewFileDTO.setReviewCode(reviewCode);
        reviewFileDTO.setFileOriginName(imageFile.getOriginalFilename());
        reviewFileDTO.setFileOriginPath("somePath");

        ReviewFile insertReview1 = modelMapper.map(reviewFileDTO, ReviewFile.class);
        reviewFileRepository.save(insertReview1);


        // Then
        Optional<ReviewFile> savedReviewFile = reviewFileRepository.findById(insertReview1.getRevFileCode());
        Assertions.assertTrue(savedReviewFile.isPresent(), "ReviewFile should be saved");


        Assertions.assertTrue(true, "Your assertion message here");
    }
    @DisplayName("기존 리뷰 수정하기 테스트")
    @Test
    public void modifyReview() throws IOException {
        //given
        String reviewTitle = "리뷰 수정 제목 2 입니다";
        String description = "리뷰 수정 상세 내용 2 입니다";
        Long campaignCode = 21L;
        Long reviewCode = 55L;

        String fileSaveName = "b4b0a64cd3c24f86a64143f1919863b4.png";

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setReviewTitle(reviewTitle);
        reviewDTO.setDescription(description);
        reviewDTO.setCampaignCode(campaignCode);

        MockMultipartFile imageFile = new MockMultipartFile(
                "file",
                "image.png",
                "image/png",
                "Some image data".getBytes()
        );

        //when
        Review review = reviewRepository.findById(reviewCode).orElseThrow(() -> new RuntimeException("Review not found"));


            review = review.reviewCode(reviewCode)
                    .reviewTitle(reviewTitle)
                    .description(description)
                    .build();

        //then
        List<Review> reviewList = reviewRepository.findAll();
        int expectedSize = 2;

        Assertions.assertEquals(expectedSize, reviewList.size());
    }


    @DisplayName("리뷰 삭제 테스트")
    @Test
    public void deleteReviewTest() {
        //given
        Long reviewCode = 54L;
        Long revFileCode = 33L;

        //when
        reviewRepository.deleteById(reviewCode);

        //then
        int expectedReview = 1;
        List<Campaign> reviewList = campaignReviewRepository.findAll();

        Assertions.assertEquals(expectedReview, reviewList.size());
    }

    @DisplayName("댓글 등록 테스트")
    @Test
    public void registCommentTest() {
        //given
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setCommentContext("댓글 테스트입니다!");
        commentDTO.setCommentWriter("user01");
        commentDTO.setCommentDate(String.valueOf(LocalDateTime.now()));
        commentDTO.setReviewCode(57L);

        //when
        Comment comment = modelMapper.map(commentDTO, Comment.class);
        reviewCommentRepository.save(comment);

        List<Comment> commentList = reviewCommentRepository.findAll();
        //then
        Assertions.assertNotNull(comment);
        Assertions.assertNotNull(commentList);

    }



}

*/
