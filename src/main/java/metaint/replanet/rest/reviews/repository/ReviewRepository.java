package metaint.replanet.rest.reviews.repository;

import metaint.replanet.rest.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {



}
