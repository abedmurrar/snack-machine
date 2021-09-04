package com.freightos;

import com.freightos.snack_machine.SnackMachine;
import com.freightos.snack_machine.SnackMachineBuilder;
import com.freightos.snack_machine.SnackMachineItem;
import com.freightos.snack_machine.money.Coin;
import com.freightos.snack_machine.money.Notes;

import java.util.ArrayList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        try {
            SnackMachineBuilder snackMachineBuilder = new SnackMachineBuilder();


            Notes twentyDolllars = new Notes(20);
            Notes fiftyDollars = new Notes(50);

            Coin tenCents = new Coin((double) 0.10);
            Coin twentyCents = new Coin((double) 0.20);
            Coin fiftyCents = new Coin((double) 0.50);
            Coin oneDollar = new Coin((double) 1);

            ArrayList<Notes> notesArrayList = new ArrayList<>();
            notesArrayList.add(twentyDolllars);
            notesArrayList.add(fiftyDollars);

            ArrayList<Coin> coinArrayList = new ArrayList<>();
            coinArrayList.add(tenCents);
            coinArrayList.add(twentyCents);
            coinArrayList.add(fiftyCents);
            coinArrayList.add(oneDollar);


            SnackMachine snackMachine = snackMachineBuilder
                    .createSnackMachine()
                    .acceptsCurrency("USD")
                    .hasCardSlot()
                    .hasNotesSlot(notesArrayList)
                    .hasCoinSlot(coinArrayList)
                    .addCoin(tenCents, 200)
                    .addCoin(twentyCents, 50)
                    .addCoin(fiftyCents, 30)
                    .addCoin(oneDollar, 120)
                    .addNotes(twentyDolllars, 150)
                    .addNotes(fiftyDollars, 130)
                    .addSnackMachineItem(0, 0, new SnackMachineItem("Lays Chips", (double) 15.6, 5))
                    .build();

            snackMachine.insertMoney(twentyDolllars).selectColumn(0).selectRow(0).order();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
