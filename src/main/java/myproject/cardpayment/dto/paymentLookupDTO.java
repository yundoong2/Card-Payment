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
    private String price;
    private String vat;

    public paymentLookupDTO(paymentEntity entity) {
        this.id = entity.getId();

    }


}
