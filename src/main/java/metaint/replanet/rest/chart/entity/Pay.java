package metaint.replanet.rest.chart.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_pay")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_code")
    private int payCode;
    @Column(name = "pay_amount")
    private int payAmount;
    @Column(name = "pay_tid")
    private String payTid;
    @JoinColumn(name = "donation_code")
    @ManyToOne
    private Donation donationCode;

    protected Pay() {}

    public Pay(int payCode, int payAmount, String payTid, Donation donationCode) {
        this.payCode = payCode;
        this.payAmount = payAmount;
        this.payTid = payTid;
        this.donationCode = donationCode;
    }

    public int getPayCode() {
        return payCode;
    }

    public int getPayAmount() {
        return payAmount;
    }

    public String getPayTid() {
        return payTid;
    }

    public Donation getDonationCode() {
        return donationCode;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "payCode=" + payCode +
                ", payAmount=" + payAmount +
                ", payTid='" + payTid + '\'' +
                ", donationCode=" + donationCode +
                '}';
    }
}
