package metaint.replanet.rest.org.entity;

import com.sun.istack.NotNull;
import lombok.*;
import metaint.replanet.rest.auth.entity.MemberRole;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Organization")
@Table(name = "tbl_org")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(toBuilder = true)
public class Organization {
    @Id
    @Column(name = "org_code")
    private int orgCode;

    @Column(name="file_origin_name")
    private String fileOriginName;

    @Column(name="file_save_name")
    private String fileSaveName;

    @Column(name="file_save_path")
    private String fileSavePath;

    @Column(name="file_extension")
    private String fileExtension;

    @Column(name = "org_description")
    private String orgDescription;

}
