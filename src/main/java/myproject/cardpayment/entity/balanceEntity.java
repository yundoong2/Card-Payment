package myproject.cardpayment.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Builder
//@Getter
@Data
@Table(name = "balance")
public class balanceEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long remainPrice;
    private Long remainVat;
}
