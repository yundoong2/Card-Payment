package myproject.cardpayment.service;

import myproject.cardpayment.dto.*;
import myproject.cardpayment.repository.paymentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static myproject.cardpayment.common.Constants.*;

@SpringBootTest
//@Transactional
class paymentServiceImplTest {
    @Autowired
    paymentRepository repository;

    @Autowired
    paymentServiceImpl paymentService;

    @Test
    void 결제() throws UnsupportedEncodingException, GeneralSecurityException {
        //given
        paymentRequestDTO requestDTO = new paymentRequestDTO();
        requestDTO.setCardNumber(CARD_NUMBER);
        requestDTO.setValidity(VALIDITY);
        requestDTO.setCvc(CVC);
        requestDTO.setInstallmentMonth(INSTALLMENT_MONTH);
        requestDTO.setPrice(PRICE);

        //when
        paymentResponseDTO dto = paymentService.doPayment(requestDTO);
        System.out.println("dto : " + dto.getId());

        //then
        paymentResponseDTO responseDTO = new paymentResponseDTO(repository.findById(dto.getId()).get());
        assertEquals(responseDTO.getId(), dto.getId());
        assertEquals(responseDTO.getResultString(), dto.getResultString());
    }

    @Test
    void 결제취소() throws GeneralSecurityException, UnsupportedEncodingException {
        //given
        paymentRequestDTO requestDTO = new paymentRequestDTO();
        requestDTO.setCardNumber(CARD_NUMBER);
        requestDTO.setValidity(VALIDITY);
        requestDTO.setCvc(CVC);
        requestDTO.setInstallmentMonth(INSTALLMENT_MONTH);
        requestDTO.setPrice(PRICE);
        requestDTO.setVat(VAT);

        //when
        paymentResponseDTO dto = paymentService.doPayment(requestDTO);
        System.out.println("dto : " + dto.getId());

        paymentCancelRequestDTO cancelDTO = new paymentCancelRequestDTO(dto.getId(), 1100L, Optional.of(100L));

        dto = paymentService.cancelPayment(cancelDTO);

        //then
        paymentLookupDTO responseDTO = paymentService.getPayment(dto.getId());

        assertEquals(responseDTO.getId(), dto.getId());
        assertEquals(responseDTO.getRemainPrice(), 9900L);
        assertEquals(responseDTO.getRemainVat(), 900L);
    }


}