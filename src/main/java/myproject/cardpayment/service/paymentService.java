package myproject.cardpayment.service;

import myproject.cardpayment.dto.*;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public interface paymentService {
    //결제
    paymentResponseDTO doPayment(paymentRequestDTO requestDTO) throws UnsupportedEncodingException, GeneralSecurityException;
    //결제 취소
    paymentResponseDTO cancelPayment(paymentCancelRequestDTO cancelRequestDTO) throws GeneralSecurityException, UnsupportedEncodingException;
    //조회
    paymentLookupDTO getPayment(String id) throws UnsupportedEncodingException, GeneralSecurityException;
}
