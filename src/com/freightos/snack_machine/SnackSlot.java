package com.freightos.snack_machine;

import java.util.HashMap;
import java.util.Map;

public class SnackSlot {
    private Map<Integer, SnackMachineItem> snacks;

    public SnackSlot() {
        this.snacks = new HashMap<>();
    }

    public Map<Integer, SnackMachineItem> getSnacks() {
        return snacks;
    }

    public SnackMachineItem getSnack(int column) {
        return this.snacks.get(column);
    }

    public SnackSlot addSnack(int column, SnackMachineItem snackMachineItem) {
        snacks.put(column, snackMachineItem);
        return this;
    }
}
