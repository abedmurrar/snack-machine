package com.freightos.snack_machine.money;


public class Coin extends Money {

    public Coin(double value) {
        super(value);
    }

    public Coin(double value, String currency, String cent) {
        super(value, currency, cent);
    }

    @Override
    public String getValueDescription() {
        if (this.getValue() < 1) {
            return (int)(this.getValue() * 100) + this.getCent();

        }
        return (int)this.getValue() + this.getCurrency();
    }

}
