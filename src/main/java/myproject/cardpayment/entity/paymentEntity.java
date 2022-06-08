package myproject.cardpayment.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
public class paymentEntity {
    @Id
    private String id;
    private String payOrCancel;
    private Long price;
    private Long vat;
    private Long installmentMonth;
    private String originId;
    private String encryptedCardInfo;
    @Column(length = 500)
    private String resultString;
    @ManyToOne
    @JoinColumn(name = "balance_id")
    private balanceEntity balance;
}
