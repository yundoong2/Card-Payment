package myproject.cardpayment.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

@Getter
@Setter
public class paymentRequestDTO {
    @NotBlank
    @Min(value = 10) @Max(value = 20)
    private String cardNumber;
    @NotBlank @Size(min = 4, max = 4)
    private String validity;
    @NotBlank @Size(min = 3, max = 3)
    private Long cvc;
    @NotBlank @Min(value = 0) @Max(value = 12)
    private Long installmentMonth;
    @NotBlank @Min(value = 100) @Max(value = 1000000000)
    private Long price;
    private Optional<Long> vat;
}
