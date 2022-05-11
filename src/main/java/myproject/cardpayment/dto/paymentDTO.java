package myproject.cardpayment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import myproject.cardpayment.entity.paymentEntity;
import myproject.cardpayment.util.UUIDGenerator;

@Data
@NoArgsConstructor
@Builder
public class paymentDTO {
    private String id;
    private String payOrCancel;
    private int price;
    private int vat;
    private int installmentMonth;
    private String originId;
    private String encryptedCardInfo;
    private String resultString;

    public paymentDTO(paymentRequestDTO requestDTO, String id, String payOrCancel, String encryptedCardInfo
            , String resultString) {
        this.id = id;
        this.payOrCancel = payOrCancel;
        this.price = requestDTO.getPrice();
        this.vat = requestDTO.getVat().get();
        this.installmentMonth = requestDTO.getInstallmentMonth();
        this.originId = "";
        this.encryptedCardInfo = encryptedCardInfo;
        this.resultString = resultString;
    }

    public paymentDTO(String id, String payOrCancel, int price, int vat, int installmentMonth, String originId, String encryptedCardInfo, String resultString) {
        this.id = id;
        this.payOrCancel = payOrCancel;
        this.price = price;
        this.vat = vat;
        this.installmentMonth = installmentMonth;
        this.originId = originId;
        this.encryptedCardInfo = encryptedCardInfo;
        this.resultString = resultString;
    }

    public paymentEntity toEntity() {
        paymentEntity entity = paymentEntity.builder()
                .id(id)
                .payOrCancel(payOrCancel)
                .price(price)
                .vat(vat)
                .installmentMonth(installmentMonth)
                .originId(originId)
                .encryptedCardInfo(encryptedCardInfo)
                .resultString(resultString)
                .build();
        return entity;
    }




}
