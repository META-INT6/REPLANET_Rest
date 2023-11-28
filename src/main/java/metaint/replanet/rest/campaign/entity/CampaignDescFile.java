package metaint.replanet.rest.campaign.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
@ToString
@AllArgsConstructor
@Getter
@Entity(name = "campaignDescFile")
@Table(name = "tbl_campaign_desc_file")
public class CampaignDescFile {

    @Id
    @Column(name = "campaign_file_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileCode; // 파일 코드
    @Column(name = "file_origin_name")
    private String fileOriginName; // 원본 파일명
    @Column(name = "file_origin_path")
    private String fileOriginPath; // 원본 파일 경로
    @Column(name = "file_save_name")
    private String fileSaveName; // 저장 파일명
    @Column(name = "file_save_path")
    private String fileSavePath; // 저장 경로
    @Column(name = "file_extension")
    private String fileExtension; // 확장자
    @ManyToOne
    @JoinColumn(name = "campaign_code")
    @ToString.Exclude
    private CampaignDescription campaignCode; // 모금 코드

    protected CampaignDescFile() {}

    public CampaignDescFile fileOriginName(String val){
        this.fileOriginName = val;
        return this;
    }

    public CampaignDescFile fileSaveName(String val){
        this.fileSaveName = val;
        return this;
    }

    public CampaignDescFile fileExtension(String val){
        this.fileExtension = val;
        return this;
    }
    public CampaignDescFile campaignCode(CampaignDescription val){
        this.campaignCode = val;
        return this;
    }
    public CampaignDescFile builder(){
        return new CampaignDescFile(
                fileCode,fileOriginName,fileOriginPath,fileSaveName,
                fileSavePath,fileExtension,campaignCode);
    }


}
