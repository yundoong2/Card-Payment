package myproject.cardpayment.service;

import myproject.cardpayment.controller.paymentForm;
import myproject.cardpayment.dto.paymentDTO;

public interface paymentService {
    //결제
    short doPayment(paymentForm paymentForm);
    //결제 취소
    short cancelPayment(paymentForm paymentForm);
    //조회
    paymentDTO getPayment(short id);
}
