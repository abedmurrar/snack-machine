package com.freightos.snack_machine.slots;

import com.freightos.snack_machine.exceptions.UnacceptedCurrencyException;
import com.freightos.snack_machine.exceptions.UnknownCoinInCoinSlot;
import com.freightos.snack_machine.exceptions.UnknownNotesInNotesSlot;
import com.freightos.snack_machine.money.Money;

public interface AcceptsMoney<T extends Money> {
    public T acceptMoney(T t) throws UnknownCoinInCoinSlot, UnacceptedCurrencyException, UnknownNotesInNotesSlot;
    public boolean validateMoney(T t);
    public boolean validateCurrency(T t);
}
