package metaint.replanet.rest.reviews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity(name="reviewPkg_campaignRevFile")
@Table(name="tbl_campaign_rev_file")
@RequiredArgsConstructor
@Getter
@ToString
public class ReviewFile {

    @Id
    @Column(name = "review_file_code", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long revFileCode;

    @Column(name = "file_origin_name")
    private String fileOriginName;

    @Column(name = "file_origin_path")
    private String fileOriginPath;

    @Column(name = "file_save_name")
    private String fileSaveName;

    @Column(name = "file_save_path")
    private String fileSavePath;

    @Column(name = "file_extension")
    private String fileExtension;

    @ManyToOne
    @JoinColumn(name = "review_code", referencedColumnName = "review_code")
    @JsonIgnore
    private Review review;

    public ReviewFile(String fileSaveName) {
    }
/*
    public ReviewFile fileOriginName(String fileOriginName) {
        this.fileOriginName = fileOriginName;
        return this;
    }
    
    public ReviewFile fileOriginPath(String fileOriginPath) {
        this.fileOriginPath = fileOriginPath;
        return this;
    }
    

    
    public ReviewFile fileSavePath(String fileSavePath) {
        this.fileSavePath = fileSavePath;
        return this;
    }
    
    public ReviewFile build() {
        return new ReviewFile(fileOriginName, fileOriginPath, fileSaveName, fileSavePath);
    }*/


    public ReviewFile fileSaveName(String fileSaveName) {
        this.fileSaveName = fileSaveName;
        return this;
    }

    public ReviewFile build() {
        return new ReviewFile(fileSaveName);
    }

}
