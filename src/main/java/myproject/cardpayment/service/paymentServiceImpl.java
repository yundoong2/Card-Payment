package myproject.cardpayment.service;

import myproject.cardpayment.dto.*;
import myproject.cardpayment.encryption.AES256Util;
import myproject.cardpayment.entity.balanceEntity;
import myproject.cardpayment.entity.paymentEntity;
import myproject.cardpayment.exception.CustomException;
import myproject.cardpayment.exception.ErrorCode;
import myproject.cardpayment.repository.balanceRepository;
import myproject.cardpayment.repository.paymentRepository;
import myproject.cardpayment.util.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Optional;
import java.util.StringJoiner;

@Service
@Transactional
public class paymentServiceImpl implements paymentService{

    public static final double VAT_RATE = 11D;
    @Autowired
    private final paymentRepository paymentRepository;
    private final balanceRepository balanceRepository;

    public paymentServiceImpl(paymentRepository paymentRepository, balanceRepository balanceRepository) {
        this.paymentRepository = paymentRepository;
        this.balanceRepository = balanceRepository;
    }

    @Override
    public paymentResponseDTO doPayment(paymentRequestDTO requestDTO) throws UnsupportedEncodingException, GeneralSecurityException {
        /*부가가치세 No Input 처리*/
        if(Optional.ofNullable(requestDTO.getVat()).isEmpty()) {
            requestDTO.setVat(Optional.of(calcVAT(requestDTO.getPrice())));
        }

        /*유효성 체크*/
        validateValidity(requestDTO.getValidity());
        validateVat(requestDTO.getPrice() , requestDTO.getVat());

        /*카드번호, 유효기간, cvc 암호화*/
        AES256Util aes256Util = new AES256Util();
        StringJoiner joiner = new StringJoiner("|");
        joiner.add(requestDTO.getCardNumber());
        joiner.add(requestDTO.getValidity());
        joiner.add(Long.toString(requestDTO.getCvc()));

        String encrypted = aes256Util.encrypt(joiner.toString());

        String totalLength = padNumSpaces(446L, 4);
        String payOrCancel = "PAYMENT";
        String id = createUniqueId();
        String originId = "";

        /*저장 String 데이터 연결*/
        StringBuilder builder = new StringBuilder();
        builder.append(totalLength);
        builder.append(padStringSpaces("PAYMENT", -10));
        builder.append(padStringSpaces(id, -20));
        builder.append(padStringSpaces(requestDTO.getCardNumber(), -20));
        builder.append(padNumZeros(requestDTO.getInstallmentMonth(), 2));
        builder.append(padStringSpaces(requestDTO.getValidity(), -4));
        builder.append(padNumSpaces(requestDTO.getCvc(), -3));
        builder.append(padNumSpaces(requestDTO.getPrice(), 10));
        builder.append(padNumSpaces(requestDTO.getVat().get(), 10));
        builder.append(padStringSpaces(originId, -20));
        builder.append(padStringSpaces(encrypted, -300));
        builder.append(padStringSpaces("", -47));
        String stringData = String.valueOf(builder);

        balanceDTO balDTO = new balanceDTO(requestDTO.getPrice(), requestDTO.getVat().get());

        balanceEntity balanceEntity = balDTO.toEntity();
        balanceRepository.save(balanceEntity);

        paymentDTO dto = new paymentDTO(requestDTO, id, payOrCancel, encrypted, stringData, balanceEntity);
        paymentEntity paymentEntity = dto.toEntity();

        return new paymentResponseDTO(paymentRepository.save(paymentEntity));
    }

    @Override
    public paymentResponseDTO cancelPayment(paymentCancelRequestDTO cancelRequestDTO) throws GeneralSecurityException, UnsupportedEncodingException {

        paymentEntity paymentEntity = paymentRepository.findById(cancelRequestDTO.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.PAYMENT_NOT_FOUND));

        if(cancelRequestDTO.getCancelPrice() > paymentEntity.getBalance().getRemainPrice()) {
            new CustomException(ErrorCode.INVALID_CANCEL_PRICE);
        }

        if(Optional.ofNullable(cancelRequestDTO.getVat()).isEmpty()) {
            cancelRequestDTO.setVat(Optional.of(paymentEntity.getVat()));
        }

        if(cancelRequestDTO.getVat().get() > paymentEntity.getBalance().getRemainVat()) {
            new CustomException(ErrorCode.INVALID_VAT_PRICE);
        }

        String totalLength = padNumSpaces(446L, 4);
        String payOrCancel = "CANCEL";
        String id = createUniqueId();
        String originId = paymentEntity.getId();

        AES256Util aes256Util = new AES256Util();
        String decInfo = aes256Util.decrypt(paymentEntity.getEncryptedCardInfo());
        String cardNumber = decInfo.split("|")[0];
        String validity = decInfo.split("|")[1];
        String cvc = decInfo.split("|")[2];

        StringBuilder builder = new StringBuilder();
        /*데이터 길이*/
        builder.append(totalLength);
        /*데이터 구분*/
        builder.append(padStringSpaces(payOrCancel, -10));
        /*관리번호*/
        builder.append(padStringSpaces(id, -20));
        /*카드번호*/
        builder.append(padStringSpaces(cardNumber, -20));
        /*할부 개월수*/
        builder.append(padNumZeros(0L, 2));
        /*카드 유효기간*/
        builder.append(padStringSpaces(validity, -4));
        /*cvc*/
        builder.append(padNumSpaces(Long.parseLong(cvc), -3));
        /*거래 금액*/
        builder.append(padNumSpaces(paymentEntity.getPrice(), 10));
        /*부가가치세*/
        builder.append(padNumSpaces(paymentEntity.getVat(), 10));
        /*원거래 관리번호*/
        builder.append(padStringSpaces(originId, -20));
        /*암호화된 카드정보*/
        builder.append(padStringSpaces(paymentEntity.getEncryptedCardInfo(), -300));
        /*예비 필드*/
        builder.append(padStringSpaces("", -47));
        String stringData = String.valueOf(builder);

        balanceEntity balanceEntity = paymentEntity.getBalance();
        balanceDTO balanceDTO = new balanceDTO(balanceEntity.getRemainPrice() - cancelRequestDTO.getCancelPrice()
                , balanceEntity.getRemainVat() - cancelRequestDTO.getVat().get());
        balanceEntity = balanceDTO.toEntity();

        balanceRepository.save(balanceEntity);

        paymentDTO paymentDTO = new paymentDTO(id, payOrCancel, cancelRequestDTO.getCancelPrice(), cancelRequestDTO.getVat().get()
                , paymentEntity.getInstallmentMonth(), originId, paymentEntity.getEncryptedCardInfo(), stringData, balanceEntity);
        paymentEntity = paymentDTO.toEntity();

        return new paymentResponseDTO(paymentRepository.save(paymentEntity));
    }

    @Override
    public paymentLookupDTO getPayment(String paymentId) throws UnsupportedEncodingException, GeneralSecurityException {
        paymentEntity paymentEntity = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new CustomException(ErrorCode.PAYMENT_NOT_FOUND));

        AES256Util aes256Util = new AES256Util();
        String decInfo = aes256Util.decrypt(paymentEntity.getEncryptedCardInfo());
        String cardNumber = decInfo.split("|")[0];
        String validity = decInfo.split("|")[1];
        String cvc = decInfo.split("|")[2];

        return new paymentLookupDTO(paymentEntity, cardNumber, validity, cvc);
    }

    //부가가치세 계산
    private long calcVAT(long price) {
        return Math.round(price / VAT_RATE);
    }

    private String padNumZeros(Long value, int length) {
        return String.format("%" + length + "d", value).replace(" ", "0");
    }

    private String padNumSpaces(Long value, int length) {
        return String.format("% " + length + "d", value);
    }

    private String padStringSpaces(String value, int length) {
        return String.format("%" + length + "s", value);
    }

    private void validateValidity(String value) {
        int month;
        int year;
        month = Integer.parseInt(value.substring(0, 2));
        year = Integer.parseInt(value.substring(2, 4));

        if (month < 1 || month > 12 || year == 0) {
            new CustomException(ErrorCode.INVALID_VALIDITY_INFO);
        }
    }

    private void validateVat(Long price, Optional<Long> vat) {
        if (vat.get() > price) {
            new CustomException(ErrorCode.INVALID_VAT_PRICE);
        }
    }

    public String createUniqueId() {
        return UUIDGenerator.createUUID();
    }





}
