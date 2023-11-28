package metaint.replanet.rest.reviews.dto;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ReviewFileDTO {

    private Long revFileCode;

    private String fileOriginName;

    private String fileOriginPath;

    private String fileSaveName;

    private String fileSavePath;

    private String fileExtension;

    private Long reviewCode;

}
