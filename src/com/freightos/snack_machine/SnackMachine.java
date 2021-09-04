package com.freightos.snack_machine;

import com.freightos.snack_machine.exceptions.*;
import com.freightos.snack_machine.money.Card;
import com.freightos.snack_machine.money.Coin;
import com.freightos.snack_machine.money.Money;
import com.freightos.snack_machine.money.Notes;
import com.freightos.snack_machine.slots.CoinSlot;
import com.freightos.snack_machine.slots.MoneySlot;
import com.freightos.snack_machine.slots.NotesSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class SnackMachine {
    final static int SNACK_SLOT_ROWS = 5;
    final static int SNACK_SLOT_COLUMNS = 5;

    private MoneySlot<Coin> coinMoneySlot;
    private MoneySlot<Notes> notesMoneySlot;
    private MoneySlot<Card> cardMoneySlot;

    private ArrayList<SnackSlot> snackSlots = new ArrayList<>();

    private Map<Coin, Integer> availableCoins = new HashMap<>();
    private Map<Notes, Integer> availableNotes = new HashMap<>();

    private ArrayList<Money> insertedMoney;
    private Integer selectedRow = null;
    private Integer selectedColumn = null;

    public SnackMachine() {
        for (byte i = 0; i < SNACK_SLOT_ROWS; i++) {
            snackSlots.add(new SnackSlot());
        }
    }

    private void deductChangeFromBalance(ArrayList<Money> change) {
        change.forEach(money -> {
            if (money instanceof Coin) {
                availableCoins.put((Coin) money, availableCoins.get(money) - 1);
            } else if (money instanceof Notes) {
                availableNotes.put((Notes) money, availableNotes.get(money) - 1);
            }
        });
    }

    private void saveInsertedMoney() {
        insertedMoney.forEach(money -> {
            if (money instanceof Coin) {
                availableCoins.put((Coin) money, availableCoins.get(money) + 1);
            } else if (money instanceof Notes) {
                availableNotes.put((Notes) money, availableNotes.get(money) + 1);
            }
        });
        insertedMoney = null;
    }

    private void validateRow(int row) throws UnknowSnackMachineRowException {
        if (!(row >= 0 && row < SNACK_SLOT_ROWS)) {
            throw new UnknowSnackMachineRowException();
        }
    }

    private void validateColumn(int column) throws UnknowSnackMachineColumnException {
        if (!(column >= 0 && column < SNACK_SLOT_COLUMNS)) {
            throw new UnknowSnackMachineColumnException();
        }
    }

    public void setSnackMachineItem(int row, int column, SnackMachineItem snackMachineItem) {
        snackSlots.get(row).addSnack(column, snackMachineItem);
    }

    public void setCoinMoneySlot(MoneySlot<Coin> coinMoneySlot) {
        this.coinMoneySlot = coinMoneySlot;
    }

    public void setNotesMoneySlot(MoneySlot<Notes> notesMoneySlot) {
        this.notesMoneySlot = notesMoneySlot;
    }

    public void setCardMoneySlot(MoneySlot<Card> cardMoneySlot) {
        this.cardMoneySlot = cardMoneySlot;
    }

    public void addAvailableNotes(Notes notes, int count) throws UnknownNotesInNotesSlot, UnacceptedCurrencyException {
        ((NotesSlot) notesMoneySlot).acceptMoney(notes);
        availableNotes.put(notes, count);
    }

    public void addAvailableCoins(Coin coin, int count) throws UnacceptedCurrencyException, UnknownCoinInCoinSlot {
        ((CoinSlot) coinMoneySlot).acceptMoney(coin);
        availableCoins.put(coin, count);
    }


    public SnackMachine insertMoney(Money money) {
        if (insertedMoney == null) {
            insertedMoney = new ArrayList<>();
        }

        try {
            if (money instanceof Card) {
                cardMoneySlot.acceptMoney((Card) money);
            } else if (money instanceof Coin) {
                coinMoneySlot.acceptMoney((Coin) money);
                availableCoins.put((Coin) money, availableCoins.get(money) + 1);
            } else if (money instanceof Notes) {
                notesMoneySlot.acceptMoney((Notes) money);
                availableNotes.put((Notes) money, availableNotes.get(money) + 1);
            }
            insertedMoney.add(money);
            double insertedAmount = Money.moneyListAmount(insertedMoney);
            System.out.println("You inserted: " + insertedAmount);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something bad happened!");
        }
        return this;
    }

    public SnackMachine selectRow(int row) throws UnknowSnackMachineRowException {
        validateRow(row);
        selectedRow = row;
        return this;
    }

    public SnackMachine selectColumn(int column) throws UnknowSnackMachineColumnException {
        validateColumn(column);
        selectedColumn = column;
        return this;
    }

    public SnackMachine order() throws SnackItemNumberNotInsertedException, SnackItemUnavailableException {
        if (selectedRow == null || selectedColumn == null) {
            throw new SnackItemNumberNotInsertedException();
        }

        SnackMachineItem snack = snackSlots.get(selectedRow).getSnack(selectedColumn);

        try {
            ArrayList<Money> change = getChange();
            snack.buy();
            saveInsertedMoney();
            deductChangeFromBalance(change);
            System.out.println("Here's your change : ");
            System.out.println(change.stream().map(m -> m.getValueDescription()).collect(Collectors.joining(",")));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Returning money");
            System.out.println(insertedMoney.stream().map(m -> m.getValueDescription()).collect(Collectors.joining(",")));
        }

        // check if can return change

        // clean up
        insertedMoney = null;
        selectedColumn = null;
        selectedRow = null;
        return this;
    }

    /**
     * If cant return change it will throw error
     *
     * @return
     */
    public ArrayList<Money> getChange() throws PriceNotMetException, NoEnoughChangeException {

        double paid = Money.moneyListAmount(insertedMoney);
        double price = snackSlots.get(selectedRow).getSnack(selectedColumn).getPrice();

        if (paid > price) {
            double expectedChange = paid - price;
            ArrayList<Money> moneyArrayList = new ArrayList<>();

            /**
             * get all notes/coins that are lower than the expected change and are available in the machine
             * then keep adding them to the change to be returned to customer
             * then check if change calculated = expected change
             * if check was correct then return change
             * else, the machine does not have enough change
             */
            availableNotes
                    .entrySet()
                    .stream()
                    .filter(e ->
                            e.getValue() > 0 && expectedChange > e.getKey().getValue()
                    ).map(e -> e.getKey())
                    .sorted()
                    .forEach(notes -> {
                        while (expectedChange - Money.moneyListAmount(moneyArrayList) > notes.getValue() && availableNotes.get(notes) > 0) {
                            moneyArrayList.add(notes);
                        }
                    });

            availableCoins
                    .entrySet()
                    .stream()
                    .filter(e ->
                            e.getValue() > 0 && expectedChange > e.getKey().getValue()
                    ).map(e -> e.getKey())
                    .sorted()
                    .forEach(coin -> {
                        while (expectedChange - Money.moneyListAmount(moneyArrayList) > coin.getValue() && availableCoins.get(coin) > 0) {
                            moneyArrayList.add(coin);
                        }
                    });
            double calculatedChange = moneyArrayList.stream().map(e -> e.getValue()).reduce((double) 0, Double::sum);

            if (expectedChange == calculatedChange) {
                return moneyArrayList;
            } else {
                throw new NoEnoughChangeException();
            }
        } else if (paid == price) {
            return null;
        } else {
            throw new PriceNotMetException();
        }
    }

}
