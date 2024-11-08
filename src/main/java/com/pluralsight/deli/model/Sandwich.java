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

    public Sandwich(String name, double startingPrice, SandwichSize size, BreadType breadType,
                    boolean isToasted, boolean isSignature) {
        super(name, startingPrice);
        this.size = size;
        this.breadType = breadType;
        this.regularToppings = new ArrayList<>();
        this.premiumToppings = new ArrayList<>();
        this.isToasted = isToasted;
        this.isSignature = false;
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

    @Override
    public double calculateProductTotal() {
        return startingPrice;
    }


}
