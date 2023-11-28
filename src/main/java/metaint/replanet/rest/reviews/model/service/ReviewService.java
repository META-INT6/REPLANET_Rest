package metaint.replanet.rest.reviews.model.service;

import metaint.replanet.rest.reviews.dto.CampaignDTO;
import metaint.replanet.rest.reviews.dto.CombineReviewDTO;
import metaint.replanet.rest.reviews.dto.ReviewDTO;
import metaint.replanet.rest.reviews.entity.Campaign;
import metaint.replanet.rest.reviews.entity.Review;
import metaint.replanet.rest.reviews.repository.CampaignRepository;
import metaint.replanet.rest.reviews.repository.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final CampaignRepository campaignRepository;
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    public ReviewService(CampaignRepository campaignRepository, ReviewRepository reviewRepository, ModelMapper modelMapper) {
        this.campaignRepository = campaignRepository;
        this.reviewRepository = reviewRepository;
        this.modelMapper = modelMapper;
    }

    public List<ReviewDTO> findAllReviews() {
        List<Review> reviewList = reviewRepository.findAll();

        System.out.println("findAllReviews: " + reviewList);

        return reviewList.stream()
                .map(review -> modelMapper.map(review, ReviewDTO.class))
                .collect(Collectors.toList());
    }


    public ReviewDTO findReviewByCampaignRevCode(Long campaignRevCode) {
        Review review = reviewRepository.findById(campaignRevCode).orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(review, ReviewDTO.class);


    }
}
