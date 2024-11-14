package com.pluralsight.deli.model;

public class Chips extends Product {

    public Chips(String name) {
        super(name, 0);
    }

    @Override
    public double calculateProductTotal() {
        return 1.50;
    }

    @Override
    public String productDetails() {
        return getName();
    }
}
