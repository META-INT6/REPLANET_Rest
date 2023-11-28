package metaint.replanet.rest.campaign.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "CampaignOrganizaion")
@Table(name = "tbl_org")
public class Organization {

    @Id
    @Column(name = "org_code")
    private int orgCode;                // 기부처 코드
    @Column(name = "file_origin_name")
    private String fileOriginName;      // 원본 파일명
    @Column(name = "file_save_name")
    private String fileSaveName;        // 저장 파일명
    @Column(name = "file_save_path")
    private String fileSavePath;        // 저장 경로
    @Column(name = "file_extension")
    private String fileExtension;       // 확장자
    @Column(name = "org_description")
    private String orgDescription;      // 기부처 한줄소개

    @OneToOne
    @JoinColumn(name = "org_code")
    private Member member;

    @Override
    public String toString() {
        return "Organization{" +
                "orgCode=" + orgCode +
                ", fileOriginName='" + fileOriginName + '\'' +
                ", fileSaveName='" + fileSaveName + '\'' +
                ", fileSavePath='" + fileSavePath + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", orgDescription='" + orgDescription + '\'' +
                ", member=" + member +
                '}';
    }
}
