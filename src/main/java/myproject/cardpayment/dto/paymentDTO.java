package myproject.cardpayment.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import myproject.cardpayment.entity.balanceEntity;
import myproject.cardpayment.entity.paymentEntity;

@Builder
@Getter
@Setter
public class paymentDTO {
    private String id;
    private String payOrCancel;
    private Long price;
    private Long vat;
    private Long installmentMonth;
    private String originId;
    private String encryptedCardInfo;
    private String resultString;
    private balanceEntity balanceEntity;

    /*결제*/
    public paymentDTO(paymentRequestDTO requestDTO, String id, String payOrCancel, String encryptedCardInfo
            , String resultString, balanceEntity balanceEntity) {
        this.id = id;
        this.payOrCancel = payOrCancel;
        this.price = requestDTO.getPrice();
        this.vat = requestDTO.getVat().get();
        this.installmentMonth = requestDTO.getInstallmentMonth();
        this.originId = "";
        this.encryptedCardInfo = encryptedCardInfo;
        this.resultString = resultString;
        this.balanceEntity = balanceEntity;
    }

    /*결제 취소*/
    public paymentDTO(String id, String payOrCancel, Long price, Long vat, Long installmentMonth, String originId, String encryptedCardInfo
            , String resultString, balanceEntity balanceEntity) {
        this.id = id;
        this.payOrCancel = payOrCancel;
        this.price = price;
        this.vat = vat;
        this.installmentMonth = installmentMonth;
        this.originId = originId;
        this.encryptedCardInfo = encryptedCardInfo;
        this.resultString = resultString;
        this.balanceEntity = balanceEntity;
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



//@Getter
//public class paymentDTO_TEST {
//    private String id;
//    private String payOrCancel;
//    private Long price;
//    private Long vat;
//    private Long installmentMonth;
//    private String originId;
//    private String encryptedCardInfo;
//    private String resultString;
//    private balanceEntity balanceEntity;
//
//    @Builder
//    public paymentDTO_TEST(~~~~){
//        this.~ = ~;
//    }
//}
