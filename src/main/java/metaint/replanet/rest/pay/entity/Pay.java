package metaint.replanet.rest.pay.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "pay_pay")
@Table(name = "tbl_pay")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(toBuilder = true)
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_code")
    private int payCode;
    @Column(name = "pay_amount")
    private int payAmount;
    @Column(name = "pay_tid")
    private String payTid;
    @ManyToOne
    @JoinColumn(name = "donation_code")
    private Donation refDonation;
}
