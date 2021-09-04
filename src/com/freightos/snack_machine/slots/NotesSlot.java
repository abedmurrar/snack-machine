package com.freightos.snack_machine.slots;

import com.freightos.snack_machine.exceptions.UnacceptedCurrencyException;
import com.freightos.snack_machine.exceptions.UnknownNotesInNotesSlot;
import com.freightos.snack_machine.money.Notes;

import java.util.ArrayList;

public class NotesSlot extends MoneySlot<Notes> {

    public NotesSlot(ArrayList<Notes> acceptedNotes, String acceptedCurrency) {
        super(acceptedNotes, acceptedCurrency);
    }

    @Override
    public Notes acceptMoney(Notes notes) throws UnknownNotesInNotesSlot, UnacceptedCurrencyException {
        if (!this.validateCurrency(notes)) {
            throw new UnacceptedCurrencyException();
        }
        if (!this.validateMoney(notes)) {
            throw new UnknownNotesInNotesSlot();
        }
        return notes;
    }

    @Override
    public boolean validateMoney(Notes notes) {
        return this.getAcceptedMoney().contains(notes);
    }

    @Override
    public boolean validateCurrency(Notes notes) {
        return notes.getCurrency().equals(this.getAcceptedCurrency());
    }
}
