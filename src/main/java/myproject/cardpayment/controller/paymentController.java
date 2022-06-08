package myproject.cardpayment.controller;

import myproject.cardpayment.dto.*;
import myproject.cardpayment.service.paymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Convert;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@RestController
@RequestMapping(paymentController.API_PAYMENT_URL)
public class paymentController {

    public static final String API_PAYMENT_URL = "/payment";
    public static final String PATH_VARIABLE_ID = "/{id}";

    private final paymentService paymentService;

    @Autowired
    public paymentController(myproject.cardpayment.service.paymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public paymentResponseDTO pay(@RequestBody @Valid paymentRequestDTO requestDTO) throws GeneralSecurityException, UnsupportedEncodingException {
        return paymentService.doPayment(requestDTO);
    }

    @PutMapping
    public paymentResponseDTO cancel(@RequestBody paymentCancelRequestDTO cancelRequestDTO) throws GeneralSecurityException, UnsupportedEncodingException {

        return paymentService.cancelPayment(cancelRequestDTO);
    }

    @GetMapping(PATH_VARIABLE_ID)
    public paymentLookupDTO getPayment(@PathVariable(name = "id") String paymentId) throws GeneralSecurityException, UnsupportedEncodingException {

        return paymentService.getPayment(paymentId);
    }
}
