package myproject.cardpayment.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private int price;
    private int vat;
    private int installmentMonth;
    private String originId;
    private String encryptedCardInfo;
    @Column(length = 500)
    private String resultString;
    private int canceledPrice;
}
