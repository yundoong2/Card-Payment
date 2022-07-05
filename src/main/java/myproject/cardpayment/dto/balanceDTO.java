package myproject.cardpayment.dto;

import lombok.*;
import myproject.cardpayment.entity.balanceEntity;
import myproject.cardpayment.entity.paymentEntity;

@Builder
@Getter
@Setter
public class balanceDTO {
    private Long remainPrice;
    private Long remainVat;

    public balanceEntity toEntity() {
        balanceEntity entity = balanceEntity.builder()
                .remainPrice(this.getRemainPrice())
                .remainVat(this.getRemainVat())
                .build();
        return entity;
    }
}
