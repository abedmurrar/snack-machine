package com.freightos.snack_machine.money;

public class Card extends Money {
    String cardNumber;

    public Card(double value, String cardNumber) {
        super(value);
        this.cardNumber = cardNumber;
    }

    public Card(double value, String currency, String cent, String cardNumber) {
        super(value, currency, cent);
        this.cardNumber = cardNumber;
    }

    @Override
    public String getValueDescription() {
        return "Card "+this.cardNumber;
    }
}
