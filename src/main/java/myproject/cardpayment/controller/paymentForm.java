package myproject.cardpayment.controller;

import java.util.Optional;

public class paymentForm {
    private short cardNumber;
    private short validity;
    private short cvc;
    private short installmentMonth;
    private int payPrice;

    public Optional<Integer> getVat() {
        return vat;
    }

    public void setVat(Optional<Integer> vat) {
        this.vat = vat;
    }

    private Optional<Integer> vat;

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

    public short getInstallment() {
        return installmentMonth;
    }

    public void setInstallment(short installment) {
        this.installmentMonth = installment;
    }

    public int getPayAmount() {
        return payPrice;
    }

    public void setPayAmount(int payAmount) {
        this.payPrice = payAmount;
    }
}
