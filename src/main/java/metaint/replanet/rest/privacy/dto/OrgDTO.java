package metaint.replanet.rest.privacy.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrgDTO {

    private int orgCode;
    private String fileOriginName;
    private String fileSaveName;
    private String fileSavePath;
    private String fileExtension;
    private String orgDescription;
}
