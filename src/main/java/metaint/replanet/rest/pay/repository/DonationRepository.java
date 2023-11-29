package metaint.replanet.rest.pay.repository;

import metaint.replanet.rest.pay.entity.Donation;
import metaint.replanet.rest.pay.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface DonationRepository extends JpaRepository<Donation, Integer> {


    List<Donation> findAllByRefMemberAndDonationDateTimeBetween(Member member, LocalDateTime stardDateTime, LocalDateTime endDateTime);
}
