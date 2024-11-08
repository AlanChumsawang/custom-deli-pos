package com.pluralsight.deli.model;

import com.pluralsight.deli.model.enums.*;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends Product {
    private SandwichSize size;
    private BreadType breadType;
    private List<RegularToppings> regularToppings;
    private List<PremiumToppings> premiumToppings;
    private boolean isToasted;
    private boolean isSignature;

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
//    public Sandwich(String name, double startingPrice, SandwichSize size, BreadType breadType, List<RegularToppings>
//            regularToppings, List<PremiumToppings> premiumToppings, boolean isToasted, boolean isSignature) {
//        super(name, startingPrice);
//        this.size = size;
//        this.breadType = breadType;
//        this.regularToppings = new ArrayList<>(regularToppings);
//        this.premiumToppings = new ArrayList<>(premiumToppings);
//        this.isToasted = isToasted;
//        this.isSignature = true;
//    }


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
        return startingPrice;
    }

    public void addRegularTopping(RegularToppings topping) {
        regularToppings.add(topping);
    }


}
