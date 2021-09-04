package com.freightos.snack_machine;

import com.freightos.snack_machine.exceptions.SnackItemUnavailableException;

public class SnackMachineItem {
    final private String name;
    final private double price;
    private int availability;

    public SnackMachineItem(String name, double price, int availability) {
        this.name = name;
        this.price = price;
        this.availability = availability;
    }


    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailability() {
        return this.availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public void buy() throws SnackItemUnavailableException {
        if (this.availability > 0) {
            this.availability--;
        } else {
            throw new SnackItemUnavailableException();
        }
    }
}
