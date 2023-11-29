package metaint.replanet.rest.pay.repository;

import metaint.replanet.rest.pay.entity.Member;
import metaint.replanet.rest.pay.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PayRepository extends JpaRepository<Pay, Integer> {

    Pay findByPayCode(int payCode);

    Pay findByRefDonation_DonationCode(int donationCode);

    List<Pay> findByRefDonationRefMember(Member member);
}
