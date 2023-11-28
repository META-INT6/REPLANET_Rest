package metaint.replanet.rest.point.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity(name = "point_file_entity")
@Table(name = "tbl_point_file")
public class PointFile {

    @Id
    @Column(name = "file_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileCode;

    @Column(name = "file_origin_name")
    private String fileOriginName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "file_save_name")
    private String fileSaveName;

    @Column(name = "application_code")
    private int applicationCode;
}
