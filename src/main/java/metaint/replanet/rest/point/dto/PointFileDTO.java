package metaint.replanet.rest.point.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PointFileDTO {

    private int fileCode;
    private String fileOriginName;
    private String filePath;
    private String fileExtension;
    private String fileSaveName;
    private int applicationCode;
}
