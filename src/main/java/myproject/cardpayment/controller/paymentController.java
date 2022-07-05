package myproject.cardpayment.controller;

import lombok.RequiredArgsConstructor;
import myproject.cardpayment.dto.*;
import myproject.cardpayment.service.paymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;


/*
 < 해야할꺼 >
 - java docs
 - 응답값 Common객체로 변경
 - Class명 앞 대문자로 변경
 - Exception 세분화
 - 에러코드, 에러메세지를 정의
 - Exception에 따라서 니가 지정한 에러코드, 에러메세지를 응답
 - TEST코드 Controller
 - package구조
   ㄴ api
     ㄴ payment
       ㄴ dto
       ㄴ PaymentController.java
     ㄴ card
   ㄴ card
      ㄴ service
      ....
   ㄴ payment
      ㄴ service
      ㄴ utils
      ㄴ dto
      ㄴ entity
   ㄴ common
      ㄴ exception
      ㄴ handler
      ㄴ encryption
   ㄴ config
 */

@RestController
@RequiredArgsConstructor
//@RequestMapping(paymentController.API_PAYMENT_URL)
public class paymentController {

//    public static final String API_PAYMENT_URL = "/payment";
//    public static final String PATH_VARIABLE_ID = "/{id}";

    private final paymentService paymentService;

//    @Autowired
//    public paymentController(myproject.cardpayment.service.paymentService paymentService) {
//        this.paymentService = paymentService;
//    }




    /**
     * 결제 기능
     *
     * @param requestDTO {@link paymentRequestDTO}
     * @return paymentResponseDTO {@link paymentResponseDTO}
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     * @author 윤혁
     * @since 2022.06.xx
     */
    @PostMapping("/payment")
    public paymentResponseDTO pay(@RequestBody @Valid paymentRequestDTO requestDTO) throws GeneralSecurityException, UnsupportedEncodingException {
        return paymentService.doPayment(requestDTO);
    }

    @PutMapping("/payment")
    public paymentResponseDTO cancel(@RequestBody paymentCancelRequestDTO cancelRequestDTO) throws GeneralSecurityException, UnsupportedEncodingException {

        return paymentService.cancelPayment(cancelRequestDTO);
    }

    // localhost:8080/payment/1234
    // localhost:8080/payment?id=1234&ddd=dd

    @GetMapping("/payment/search/{id}")
    public CommonResultDto getPayment(@PathVariable(name = "id") String paymentId) throws GeneralSecurityException, UnsupportedEncodingException {

        return CommonResultDto.success(paymentService.getPayment(paymentId));
    }
}
