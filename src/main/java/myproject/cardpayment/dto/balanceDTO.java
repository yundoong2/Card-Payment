package myproject.cardpayment.dto;

import lombok.*;
import myproject.cardpayment.entity.balanceEntity;
import myproject.cardpayment.entity.paymentEntity;

@Data
public class balanceDTO {
    private Long remainPrice;
    private Long remainVat;

    public balanceDTO(Long remainPrice, Long remainVat) {
        this.remainPrice = remainPrice;
        this.remainVat = remainVat;
    }

    public balanceEntity toEntity() {
        balanceEntity entity = balanceEntity.builder()
                .remainPrice(this.getRemainPrice())
                .remainVat(this.getRemainVat())
                .build();
        return entity;
    }
}
