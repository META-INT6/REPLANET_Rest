package metaint.replanet.rest.configuration;


import metaint.replanet.rest.reviews.dto.ReviewDTO;
import metaint.replanet.rest.reviews.entity.Review;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configure the ModelMapper to ignore ambiguity
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        //       TypeMap<Review, ReviewDTO> reviewToReviewDTO = modelMapper.createTypeMap(Review.class, ReviewDTO.class);
//        reviewToReviewDTO.addMapping(src -> src.getCampaign().getCampaignCode(), ReviewDTO::setCampaignCode);
//        reviewToReviewDTO.addMapping(src -> src.getCampaign().getCampaignTitle(), ReviewDTO::setCampaignTitle);
//        reviewToReviewDTO.addMapping(src -> src.getCampaign().getCampaignContent(), ReviewDTO::setCampaignContent);
//        reviewToReviewDTO.addMapping(src -> src.getCampaign().getCampaignCategory(), ReviewDTO:: setCampaignCategory);
//        reviewToReviewDTO.addMapping(src -> src.getCampaign().getCurrentBudget(), ReviewDTO:: setCurrentBudget);
//        reviewToReviewDTO.addMapping(src -> src.getCampaign().getGoalBudget(), ReviewDTO:: setGoalBudget);
//        reviewToReviewDTO.addMapping(src -> src.getCampaign().getOrgName(), ReviewDTO:: setOrgName);
//        reviewToReviewDTO.addMapping(src -> src.getCampaign().getOrgDescription(), ReviewDTO:: setOrgDescription);
//        reviewToReviewDTO.addMapping(src -> src.getCampaign().getOrgTel(), ReviewDTO:: setOrgTel);

        // Add mappings for other Campaign properties.

        return modelMapper;
    }
}


