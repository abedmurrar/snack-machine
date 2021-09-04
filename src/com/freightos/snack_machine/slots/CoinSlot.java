package com.freightos.snack_machine.slots;

import com.freightos.snack_machine.exceptions.UnacceptedCurrencyException;
import com.freightos.snack_machine.exceptions.UnknownCoinInCoinSlot;
import com.freightos.snack_machine.money.Coin;

import java.util.ArrayList;

public class CoinSlot extends MoneySlot<Coin> {

    public CoinSlot(ArrayList<Coin> acceptedCoins, String acceptedCurrency) {
        super(acceptedCoins, acceptedCurrency);
    }

    @Override
    public Coin acceptMoney(Coin coin) throws UnknownCoinInCoinSlot, UnacceptedCurrencyException {
        if (!this.validateCurrency(coin)) {
            throw new UnacceptedCurrencyException();
        }
        if (!this.validateMoney(coin)) {
            throw new UnknownCoinInCoinSlot();
        }
        return coin;
    }

    @Override
    public boolean validateMoney(Coin coin) {
        return this.getAcceptedMoney().contains(coin);
    }

    @Override
    public boolean validateCurrency(Coin coin) {
        return coin.getCurrency().equals(this.getAcceptedCurrency());
    }
}
