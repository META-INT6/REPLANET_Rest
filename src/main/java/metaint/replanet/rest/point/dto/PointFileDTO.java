package metaint.replanet.rest.point.dto;

public class PointFileDTO {


    private int fileCode;

    private String fileOriginName;

    private String filePath;

    private String fileExtension;

    private String fileSaveName;

    private int applicationCode;

    public PointFileDTO() {
    }

    public PointFileDTO(int fileCode, String fileOriginName, String filePath, String fileExtension, String fileSaveName, int applicationCode) {
        this.fileCode = fileCode;
        this.fileOriginName = fileOriginName;
        this.filePath = filePath;
        this.fileExtension = fileExtension;
        this.fileSaveName = fileSaveName;
        this.applicationCode = applicationCode;
    }

    public int getFileCode() {
        return fileCode;
    }

    public void setFileCode(int fileCode) {
        this.fileCode = fileCode;
    }

    public String getFileOriginName() {
        return fileOriginName;
    }

    public void setFileOriginName(String fileOriginName) {
        this.fileOriginName = fileOriginName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileSaveName() {
        return fileSaveName;
    }

    public void setFileSaveName(String fileSaveName) {
        this.fileSaveName = fileSaveName;
    }

    public int getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(int applicationCode) {
        this.applicationCode = applicationCode;
    }

    @Override
    public String toString() {
        return "PointFileDTO{" +
                "fileCode=" + fileCode +
                ", fileOriginName='" + fileOriginName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", fileSaveName='" + fileSaveName + '\'' +
                ", applicationCode=" + applicationCode +
                '}';
    }
}
