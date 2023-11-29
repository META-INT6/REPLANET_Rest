package metaint.replanet.rest.pay.dto.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationAmountDTO {
    private int cashAmount;
    private int pointAmount;
    private int finalAmount;
}
