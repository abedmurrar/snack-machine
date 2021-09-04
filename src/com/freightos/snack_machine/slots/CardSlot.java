package com.freightos.snack_machine.slots;

import com.freightos.snack_machine.exceptions.UnacceptedCurrencyException;
import com.freightos.snack_machine.money.Card;

public class CardSlot extends MoneySlot<Card> {

    public CardSlot(String acceptedCurrency) {
        // accept all cards
        super(null, acceptedCurrency);
    }

    @Override
    public Card acceptMoney(Card card) throws UnacceptedCurrencyException {
        if (!this.validateCurrency(card)) {
            throw new UnacceptedCurrencyException();
        }

//        if(!this.validateMoney(card)){
//
//        }


        return card;
    }

    @Override
    public boolean validateMoney(Card notes) {
        // always accept card
        return true;
    }

    @Override
    public boolean validateCurrency(Card card) {
        return card.getCurrency().equals(this.getAcceptedCurrency());
    }
}
