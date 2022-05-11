package myproject.cardpayment.service;

import myproject.cardpayment.dto.paymentCancelRequestDTO;
import myproject.cardpayment.dto.paymentDTO;
import myproject.cardpayment.dto.paymentRequestDTO;
import myproject.cardpayment.dto.paymentResponseDTO;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public interface paymentService {
    //결제
    paymentResponseDTO doPayment(paymentRequestDTO requestDTO) throws UnsupportedEncodingException, GeneralSecurityException;
    //결제 취소
    paymentResponseDTO cancelPayment(paymentCancelRequestDTO cancelRequestDTO) throws GeneralSecurityException, UnsupportedEncodingException;
    //조회
    paymentDTO getPayment(short id);
}
