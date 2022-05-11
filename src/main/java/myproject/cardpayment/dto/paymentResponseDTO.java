package myproject.cardpayment.dto;

import lombok.Getter;
import myproject.cardpayment.entity.paymentEntity;

@Getter
public class paymentResponseDTO {
    private String id;
    private String resultString;

    public paymentResponseDTO(paymentEntity entity) {
        this.id = entity.getId();
        this.resultString = entity.getResultString();
    }
}
