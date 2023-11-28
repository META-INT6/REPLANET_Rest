package metaint.replanet.rest.point.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExchangeDTO {

    private int exchangeCode;
    private Date exchangeDate;
    private String title;
    private String status;
    private Date processingDate;
    private String returnDetail;
    private int points;
    private int memberCode;
}
