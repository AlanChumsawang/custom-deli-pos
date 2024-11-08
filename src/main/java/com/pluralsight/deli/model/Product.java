package com.pluralsight.deli.model;

public abstract class Product {
    protected String name;
    protected double startingPrice;

    public Product(String name, double startingPrice) {
        this.name = name;
        this.startingPrice = startingPrice;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return startingPrice;
    }

    public abstract double calculateProductTotal();
}
