package myproject.cardpayment.service;

import myproject.cardpayment.dto.paymentDTO;
import myproject.cardpayment.dto.paymentRequestDTO;
import myproject.cardpayment.dto.paymentResponseDTO;
import myproject.cardpayment.encryption.AES256Util;
import myproject.cardpayment.entity.paymentEntity;
import myproject.cardpayment.exception.CustomException;
import myproject.cardpayment.exception.ErrorCode;
import myproject.cardpayment.repository.paymentRepository;
import myproject.cardpayment.util.UUIDGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Optional;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class paymentServiceImplTest {
    @Autowired
    paymentRepository repository;

    @Autowired
    paymentServiceImpl paymentService;

    @Test
    void 결제() throws UnsupportedEncodingException, GeneralSecurityException {
        //given
        paymentRequestDTO requestDTO = new paymentRequestDTO();
        requestDTO.setCardNumber("1111222233334444");
        requestDTO.setValidity("1024");
        requestDTO.setCvc(333L);
        requestDTO.setInstallmentMonth(0L);
        requestDTO.setPrice(100000L);

        //when
        paymentResponseDTO dto = paymentService.doPayment(requestDTO);
        System.out.println("dto : " + dto.getId());

        //then
        paymentResponseDTO responseDTO = new paymentResponseDTO(repository.findById(dto.getId()).get());
        assertEquals(responseDTO.getId(), dto.getId());
        assertEquals(responseDTO.getResultString(), dto.getResultString());
    }


}