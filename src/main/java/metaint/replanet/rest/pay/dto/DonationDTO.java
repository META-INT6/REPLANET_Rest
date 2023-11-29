package metaint.replanet.rest.pay.dto;

import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DonationDTO {
    private int donationCode;
    private LocalDateTime donationDateTime;
    private int donationPoint;
    private int refMemberCode;
    private int refCampaignCode;
}
