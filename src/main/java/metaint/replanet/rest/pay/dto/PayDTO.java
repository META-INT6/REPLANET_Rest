package metaint.replanet.rest.pay.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PayDTO {
    private int payCode;
    private int payAmount;
    private String payTid;
    private int refDonationCode;
}
