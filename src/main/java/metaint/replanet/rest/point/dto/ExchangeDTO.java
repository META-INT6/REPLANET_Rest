package metaint.replanet.rest.point.dto;

import java.util.Date;

public class ExchangeDTO {

    private int exchangeCode;
    private Date exchangeDate;
    private String title;
    private String status;
    private String returnDetail;
    private int points;
    private int memberCode;

    public ExchangeDTO() {
    }

    public ExchangeDTO(int exchangeCode, Date exchangeDate, String title, String status, String returnDetail, int points, int memberCode) {
        this.exchangeCode = exchangeCode;
        this.exchangeDate = exchangeDate;
        this.title = title;
        this.status = status;
        this.returnDetail = returnDetail;
        this.points = points;
        this.memberCode = memberCode;
    }

    public int getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(int exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturnDetail() {
        return returnDetail;
    }

    public void setReturnDetail(String returnDetail) {
        this.returnDetail = returnDetail;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    @Override
    public String toString() {
        return "PointExchange{" +
                "exchangeCode=" + exchangeCode +
                ", exchangeDate=" + exchangeDate +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                ", returnDetail='" + returnDetail + '\'' +
                ", points=" + points +
                ", memberCode=" + memberCode +
                '}';
    }
}
