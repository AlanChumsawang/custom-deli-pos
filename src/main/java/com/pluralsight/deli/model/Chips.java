package com.pluralsight.deli.model;

public class Chips extends Product {
    protected String name;

    public Chips(String name) {
        super(name, 0);
    }

    @Override
    public double calculateProductTotal() {
        return 1.50;
    }
}
