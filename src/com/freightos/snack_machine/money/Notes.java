package com.freightos.snack_machine.money;

public class Notes extends Money {

    public Notes(double value) {
        super(value);
    }

    public Notes(double value, String currency, String cent) {
        super(value, currency, cent);
    }

    @Override
    public String getValueDescription() {
        return this.getValue() + this.getCurrency();
    }
}
