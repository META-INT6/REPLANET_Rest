package metaint.replanet.rest.privacy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity(name = "organization1")
@Table(name = "tbl_org")
public class Organization {

    @Id
    @Column(name = "org_code")
    private int orgCode;

    @Column(name = "file_origin_name")
    private String fileOriginName;

    @Column(name = "file_save_name")
    private String fileSaveName;

    @Column(name = "file_save_path")
    private String fileSavePath;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "org_description")
    private String orgDescription;

    public Organization orgDescription(String val){
        this.orgDescription = val;
        return this;
    }

    public Organization builder(){
        return new Organization(orgCode, fileOriginName, fileSaveName, fileSavePath, fileExtension, orgDescription);
    }
}
