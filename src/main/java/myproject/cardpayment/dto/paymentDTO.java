package myproject.cardpayment.dto;

public class paymentDTO {
    private short id;
    private short cardNumber;
    private short validity;
    private short cvc;
    private short installmentMonth;
    private int payPrice;
    private int vat;
    private String originId;
    private String encryptedCardInfo;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public short getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(short cardNumber) {
        this.cardNumber = cardNumber;
    }

    public short getValidity() {
        return validity;
    }

    public void setValidity(short validity) {
        this.validity = validity;
    }

    public short getCvc() {
        return cvc;
    }

    public void setCvc(short cvc) {
        this.cvc = cvc;
    }

    public short getInstallmentMonth() {
        return installmentMonth;
    }

    public void setInstallmentMonth(short installmentMonth) {
        this.installmentMonth = installmentMonth;
    }

    public int getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(int payPrice) {
        this.payPrice = payPrice;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public String getOriMgmtNumber() {
        return originId;
    }

    public void setOriMgmtNumber(String oriMgmtNumber) {
        this.originId = oriMgmtNumber;
    }

    public String getEncryptedCardInfo() {
        return encryptedCardInfo;
    }

    public void setEncryptedCardInfo(String encryptedCardInfo) {
        this.encryptedCardInfo = encryptedCardInfo;
    }
}
