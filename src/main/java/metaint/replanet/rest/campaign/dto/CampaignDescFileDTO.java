package metaint.replanet.rest.campaign.dto;

import lombok.*;
import metaint.replanet.rest.campaign.entity.CampaignDescription;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CampaignDescFileDTO {

    private int fileCode; // 파일 코드
    private String fileOriginName; // 원본 파일명
    private String fileOriginPath; // 원본 파일 경로
    private String fileSaveName; // 저장 파일명
    private String fileSavePath; // 저장 경로
    private String fileExtension; // 확장자
    private int campaignCode; // 모금 코드
    //private String imageURL;

    public CampaignDescFileDTO(String fileOriginName, String fileSaveName, String fileSavePath, String fileExtension, int campaignCode) {
        this.fileOriginName = fileOriginName;
        this.fileSaveName = fileSaveName;
        this.fileSavePath = fileSavePath;
        this.fileExtension = fileExtension;
        this.campaignCode = campaignCode;
    }
}
