package test;

import com.freightos.snack_machine.SnackMachine;
import com.freightos.snack_machine.SnackMachineBuilder;
import com.freightos.snack_machine.SnackMachineItem;
import com.freightos.snack_machine.exceptions.NoEnoughChangeException;
import com.freightos.snack_machine.money.Coin;
import com.freightos.snack_machine.money.Notes;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;

class MainTest {
    @Test(expected = NoEnoughChangeException.class)
    @DisplayName("Snack Machine should not have change")
    void testHaveChange() throws Exception {
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
                .addCoin(tenCents, 0)
                .addCoin(twentyCents, 0)
                .addCoin(fiftyCents, 0)
                .addCoin(oneDollar, 0)
                .addNotes(twentyDolllars, 0)
                .addNotes(fiftyDollars, 0)
                .addSnackMachineItem(0, 0, new SnackMachineItem("Lays Chips", (double) 15.6, 5))
                .build();

        snackMachine.insertMoney(twentyDolllars).selectColumn(0).selectRow(0).order();
    }


}