package metaint.replanet.rest.reviews.repository;


import metaint.replanet.rest.reviews.entity.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    List<ReviewComment> findByReviewCode(Long reviewCode);

    List<ReviewComment> findAllByReviewCode(Long reviewCode);

    void deleteByReviewCode(Long reviewCode);

    @Modifying
    @Transactional
    @Query("UPDATE ReviewComment rc SET rc.revCommentMonitorized = 'Y' WHERE rc.revCommentCode = :revCommentCode")
    void updateRevCommentMonitorized(@Param("revCommentCode") Long revCommentCode);
}
