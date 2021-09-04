package com.freightos.snack_machine.money;

import java.util.ArrayList;

/**
 * By default it is considered a US Dollar
 */
public abstract class Money implements Comparable<Money> {
    private String currency = "USD";
    private String cent = "c";
    private final double value;

    protected Money(double value) {
        this.value = value;
    }

    protected Money(double value, String currency, String cent) {
        this.value = value;
        this.cent = cent;
        this.currency = currency;
    }

    public double getValue() {
        return this.value;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getCent() {
        return this.cent;
    }

    static public double moneyListAmount(ArrayList<Money> moneyArrayList) {
        return moneyArrayList.stream().map(e -> e.getValue()).reduce((double) 0, Double::sum);
    }

    abstract public String getValueDescription();

    /**
     * checks if values and currencies are the same
     *
     * @param other Money
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Money) {
            return this.getValue() == ((Money) other).getValue() && this.getCurrency().equals(((Money) other).getCurrency());
        } else return false;
    }

    @Override
    public int compareTo(Money money) {
        return (int) ((money.getValue() - this.getValue()) * 100);
    }

}
