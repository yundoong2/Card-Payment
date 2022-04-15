package myproject.cardpayment.service;

import myproject.cardpayment.controller.paymentForm;
import myproject.cardpayment.dto.paymentDTO;

public class paymentServiceImpl implements paymentService{
    @Override
    public short doPayment(paymentForm paymentForm) {
        return 0;
    }

    @Override
    public short cancelPayment(paymentForm paymentForm) {
        return 0;
    }

    @Override
    public paymentDTO getPayment(short id) {
        return null;
    }
}
