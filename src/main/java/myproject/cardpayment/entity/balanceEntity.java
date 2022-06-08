package myproject.cardpayment.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@Getter
public class balanceEntity {
    @Id
    @GeneratedValue
    @Column(name = "balance_id")
    private Long id;
    private Long remainPrice;
    private Long remainVat;
}
