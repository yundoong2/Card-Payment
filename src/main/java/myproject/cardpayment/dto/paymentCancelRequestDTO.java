package myproject.cardpayment.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.util.Optional;

@Getter
@Setter
public class paymentCancelRequestDTO {

    private String id;
    private int cancelPrice;
    private Optional<Integer> vat;
}
