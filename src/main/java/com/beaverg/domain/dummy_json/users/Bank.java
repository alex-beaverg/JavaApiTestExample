package com.beaverg.domain.dummy_json.users;

import java.util.Objects;

public class Bank {
    private String cardExpire;
    private String cardNumber;
    private String cardType;
    private String currency;
    private String iban;

    public Bank() { }

    public String getCardExpire() {
        return cardExpire;
    }

    public void setCardExpire(String cardExpire) {
        this.cardExpire = cardExpire;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(cardExpire, bank.cardExpire) &&
                Objects.equals(cardNumber, bank.cardNumber) &&
                Objects.equals(cardType, bank.cardType) &&
                Objects.equals(currency, bank.currency) &&
                Objects.equals(iban, bank.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardExpire, cardNumber, cardType, currency, iban);
    }
}
