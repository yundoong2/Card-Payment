package myproject.cardpayment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_VALIDITY_INFO(HttpStatus.BAD_REQUEST, "카드 유효기간이 유효하지 않습니다."),
    INVALID_CANCEL_PRICE(HttpStatus.BAD_REQUEST, "취소 금액은 결제 금액보다 작아야 합니다."),
    INVALID_VAT_PRICE(HttpStatus.BAD_REQUEST, "거래 금액보다 작아야 합니다."),
    INVALID_VAT_CANCEL_PRICE(HttpStatus.BAD_REQUEST, "원 거래 금액의 부가가치세와 총 쉬초금액의 부가가치세의 합과 같아야 합니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "결제 정보를 찾을 수 없습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "데이터가 이미 존재합니다."),

    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
