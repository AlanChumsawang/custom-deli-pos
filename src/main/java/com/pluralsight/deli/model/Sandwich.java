package com.pluralsight.deli.model;

import com.pluralsight.deli.model.enums.*;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends Product {
    protected SandwichSize size;
    protected BreadType breadType;
    protected List<RegularToppings> regularToppings;
    protected List<PremiumToppings> premiumToppings;
    protected boolean isToasted;
    protected boolean isSignature;

    public Sandwich(String name, SandwichSize size, BreadType breadType,
                    boolean isToasted, boolean isSignature) {
        super(name, 0);
        this.size = size;
        this.breadType = breadType;
        this.regularToppings = new ArrayList<>();
        this.premiumToppings = new ArrayList<>();
        this.isToasted = isToasted;
        this.isSignature = false;

        if (size == SandwichSize.SMALL) {
            this.startingPrice = 5.50;
        } else if (size == SandwichSize.MEDIUM) {
            this.startingPrice = 7.00;
        } else if (size == SandwichSize.LARGE) {
            this.startingPrice = 8.50;
        }
    }



    public SandwichSize getSize() {
        return size;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public List<RegularToppings> getRegularToppings() {
        return regularToppings;
    }

    public List<PremiumToppings> getPremiumToppings() {
        return premiumToppings;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public boolean isSignature() {
        return isSignature;
    }

    @Override
    public double calculateProductTotal() {
        double total = startingPrice; //product total
        for (PremiumToppings topping : premiumToppings) { //iterating through premium toppings
            if (topping.getType() == PremiumToppings.Type.MEAT) { //conditional one if the topping is meat
                if (size == SandwichSize.SMALL) {
                    total += 1.00;
                } else if (size == SandwichSize.MEDIUM) {
                    total += 2.00;
                } else if (size == SandwichSize.LARGE) {
                    total += 3.00;
                }
            } else if (topping.getType() == PremiumToppings.Type.CHEESE) {
                if (size == SandwichSize.SMALL) {
                    total += 0.75;
                } else if (size == SandwichSize.MEDIUM) {
                    total += 1.50;
                } else if (size == SandwichSize.LARGE) {
                    total += 2.25;
                }
            }
        }
        return total;
    }

    public void addRegularTopping(RegularToppings topping) {
        regularToppings.add(topping);
    }

    public void addPremiumTopping(PremiumToppings topping) {
        premiumToppings.add(topping);
    }


}
