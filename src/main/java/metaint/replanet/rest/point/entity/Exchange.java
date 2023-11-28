package metaint.replanet.rest.point.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@DynamicInsert
@Entity(name = "point_exchange_entity")
@Table(name = "tbl_point_exchange")
public class Exchange {

    @Id
    @Column(name = "exchange_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int exchangeCode;

    @Column(name = "exchange_date")
    private Date exchangeDate;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;

    @Column(name = "processing_date")
    private Date processingDate;

    @Column(name = "return_detail")
    private String returnDetail;

    @Column(name = "points")
    private int points;

    @Column(name = "member_code")
    private int memberCode;

    public Exchange status(String val){
        this.status = val;
        return this;
    }

    public Exchange processingDate(Date val){
        this.processingDate = val;
        return this;
    }

    public Exchange returnDetail(String val){
        this.returnDetail = val;
        return this;
    }

    public Exchange points(int val){
        this.points = val;
        return this;
    }

    public Exchange builder(){
        return new Exchange(exchangeCode, exchangeDate, title, status, processingDate, returnDetail, points, memberCode);
    }
}
