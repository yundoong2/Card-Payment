package myproject.cardpayment.dto;

import lombok.Getter;
import lombok.Setter;
import myproject.cardpayment.entity.paymentEntity;

@Getter
public class paymentLookupDTO {
    private String id;
    private String cardNumber;
    private String validity;
    private String cvc;
    private String payOrCancel;
    private Long price;
    private Long vat;
    private Long remainPrice;
    private Long remainVat;

    public paymentLookupDTO(paymentEntity entity, String cardNumber, String validity, String cvc) {
        this.id = entity.getId();
        this.cardNumber = cardNumber;
        this.validity = validity;
        this.cvc = cvc;
        this.payOrCancel = entity.getPayOrCancel();
        this.price = entity.getPrice();
        this.vat = entity.getVat();
        this.remainPrice = entity.getBalance().getRemainPrice();
        this.remainVat = entity.getBalance().getRemainVat();
    }


}
