package metaint.replanet.rest.reviews.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "reviewpkg_organization")
@Table(name = "tbl_org")
@NoArgsConstructor
@Getter
//@ToString
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "org_code")
    private Long orgCode;

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

    @OneToMany(mappedBy = "organization")
    @JsonBackReference
    private List<Campaign> campaign;

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "member_code", referencedColumnName = "member_code")
    private Member member;



}
