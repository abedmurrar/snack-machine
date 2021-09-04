package com.freightos.snack_machine;

import com.freightos.snack_machine.exceptions.UnacceptedCurrencyException;
import com.freightos.snack_machine.exceptions.UnknownCoinInCoinSlot;
import com.freightos.snack_machine.exceptions.UnknownNotesInNotesSlot;
import com.freightos.snack_machine.money.Card;
import com.freightos.snack_machine.money.Coin;
import com.freightos.snack_machine.money.Notes;
import com.freightos.snack_machine.slots.CardSlot;
import com.freightos.snack_machine.slots.CoinSlot;
import com.freightos.snack_machine.slots.MoneySlot;
import com.freightos.snack_machine.slots.NotesSlot;

import java.util.ArrayList;

public class SnackMachineBuilder {
    private SnackMachine snackMachine;
    private String acceptedCurrency;

    public SnackMachineBuilder createSnackMachine() {
        this.snackMachine = new SnackMachine();
        return this;
    }

    public SnackMachineBuilder acceptsCurrency(String acceptedCurrency) {
        this.acceptedCurrency = acceptedCurrency;
        return this;
    }

    public SnackMachineBuilder hasCoinSlot(ArrayList<Coin> acceptedCoins) {
        MoneySlot<Coin> coinMoneySlot = new CoinSlot(acceptedCoins, this.acceptedCurrency);
        this.snackMachine.setCoinMoneySlot(coinMoneySlot);
        return this;
    }

    public SnackMachineBuilder hasCardSlot() {
        MoneySlot<Card> cardMoneySlot = new CardSlot(this.acceptedCurrency);
        this.snackMachine.setCardMoneySlot(cardMoneySlot);
        return this;
    }

    public SnackMachineBuilder hasNotesSlot(ArrayList<Notes> acceptedNotes) {
        MoneySlot<Notes> notesMoneySlot = new NotesSlot(acceptedNotes, this.acceptedCurrency);
        this.snackMachine.setNotesMoneySlot(notesMoneySlot);
        return this;
    }

    public SnackMachineBuilder addSnackMachineItem(int row, int column, SnackMachineItem snackMachineItem) {
        this.snackMachine.setSnackMachineItem(row, column, snackMachineItem);
        return this;
    }

    public SnackMachineBuilder addCoin(Coin coin, int count) throws UnacceptedCurrencyException, UnknownCoinInCoinSlot {
        this.snackMachine.addAvailableCoins(coin, count);
        return this;
    }

    public SnackMachineBuilder addNotes(Notes notes, int count) throws UnacceptedCurrencyException, UnknownNotesInNotesSlot {
        this.snackMachine.addAvailableNotes(notes, count);
        return this;
    }

    public SnackMachine build() {
        return this.snackMachine;
    }
}
