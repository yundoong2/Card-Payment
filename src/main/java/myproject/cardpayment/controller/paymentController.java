package myproject.cardpayment.controller;

import myproject.cardpayment.dto.paymentCancelRequestDTO;
import myproject.cardpayment.dto.paymentRequestDTO;
import myproject.cardpayment.dto.paymentResponseDTO;
import myproject.cardpayment.service.paymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Convert;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@RestController
public class paymentController {

    private final paymentService paymentService;

    @Autowired
    public paymentController(myproject.cardpayment.service.paymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public paymentResponseDTO pay(@RequestBody @Valid paymentRequestDTO requestDTO) throws GeneralSecurityException, UnsupportedEncodingException {
        return paymentService.doPayment(requestDTO);
    }

    @RequestMapping(value = "/payment", method = RequestMethod.PUT)
    public paymentResponseDTO cancel(@RequestBody paymentCancelRequestDTO cancelRequestDTO) throws GeneralSecurityException, UnsupportedEncodingException {

        return paymentService.cancelPayment(cancelRequestDTO);
    }

    @RequestMapping(value = "/payment/id", method = RequestMethod.GET)
    public String getPayment() {

        return "ok";
    }
}
