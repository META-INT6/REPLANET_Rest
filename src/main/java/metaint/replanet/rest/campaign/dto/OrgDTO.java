package metaint.replanet.rest.campaign.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrgDTO {
    private int orgCode;
    private String fileOriginName; // 원본 파일명
    private String fileSaveName; // 저장 파일명
    private String fileSavePath; // 저장 경로
    private String fileExtension; // 확장자
    private String orgDescription; // 기부처 소개

    private MemberDTO member; //


}
