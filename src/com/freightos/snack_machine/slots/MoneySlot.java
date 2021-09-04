package com.freightos.snack_machine.slots;

import com.freightos.snack_machine.money.Money;

import java.util.ArrayList;

abstract public class MoneySlot<T extends Money> implements AcceptsMoney<T> {
    final private ArrayList<T> acceptedMoney;
    final private String acceptedCurrency;


    protected MoneySlot(ArrayList<T> acceptedMoney, String acceptedCurrency) {
        this.acceptedMoney = acceptedMoney;
        this.acceptedCurrency = acceptedCurrency;
    }

    protected ArrayList<T> getAcceptedMoney() {
        return acceptedMoney;
    }

    protected String getAcceptedCurrency() {
        return acceptedCurrency;
    }
}
