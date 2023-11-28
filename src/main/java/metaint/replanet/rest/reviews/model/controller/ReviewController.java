package metaint.replanet.rest.reviews.model.controller;

import metaint.replanet.rest.reviews.dto.CombineReviewDTO;
import metaint.replanet.rest.reviews.dto.ReviewDTO;
import metaint.replanet.rest.reviews.entity.Review;
import metaint.replanet.rest.reviews.model.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "http://localhost:3001")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping("/")
    public ResponseEntity<List<ReviewDTO>> getReviewList() {

        System.out.println("(ReviewController) 들어옴");

        List<ReviewDTO> details = reviewService.findAllReviews();

        System.out.println("(review controller): 가져온 총 결과 : " +details);
        return  ResponseEntity.ok(details);

    }

    @GetMapping("/{campaignRevCode}")
    public ResponseEntity<ReviewDTO> getSpecificReview(@PathVariable Long campaignRevCode) {
        System.out.println("(ReviewController) getSpecificReview code : " + campaignRevCode);
        ReviewDTO details = reviewService.findReviewByCampaignRevCode(campaignRevCode);

        System.out.println("(review controller): 가져온 총 결과 : " +details);
        return ResponseEntity.ok(details);
    }

    //@PostMapping("/reviews")

}
