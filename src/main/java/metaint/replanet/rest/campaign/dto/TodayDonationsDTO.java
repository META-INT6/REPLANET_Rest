package metaint.replanet.rest.campaign.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TodayDonationsDTO {
    int payCode;
    int payAmount;
    String donationDate; // 기부한 날짜
    int donationCount; // 기부갯수
    int totalDonation; // 총 기부금
}
