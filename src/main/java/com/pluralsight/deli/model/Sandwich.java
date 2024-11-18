package com.pluralsight.deli.model;

import com.pluralsight.deli.model.enums.*;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends Product {
    protected SandwichSize size;
    protected BreadType breadType;
    protected List<RegularToppings> regularToppings;
    protected List<PremiumToppings> premiumToppings;
    protected List<Sauces> sauces;
    protected boolean isToasted;
    protected boolean extraMeat;
    protected boolean extraCheese;

    public Sandwich(String name, SandwichSize size, BreadType breadType,
                    boolean isToasted) {
        super(name, 0);
        this.size = size;
        this.breadType = breadType;
        this.regularToppings = new ArrayList<>();
        this.premiumToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.isToasted = isToasted;
        this.extraMeat = false;

        if (size == SandwichSize.SMALL) {
            this.startingPrice = 5.50;
        } else if (size == SandwichSize.MEDIUM) {
            this.startingPrice = 7.00;
        } else if (size == SandwichSize.LARGE) {
            this.startingPrice = 8.50;
        }
    }

    public Sandwich(String name, SandwichSize size, BreadType breadType,
                    boolean isToasted, List<RegularToppings> regularToppings, List<PremiumToppings> premiumToppings, List<Sauces> sauces) {
        super(name, 0);
        this.size = size;
        this.breadType = breadType;
        this.regularToppings = regularToppings;
        this.premiumToppings = premiumToppings;
        this.isToasted = isToasted;
        this.sauces = sauces;
        this.extraMeat = false;

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


    public boolean isExtraMeat() {
        return extraMeat;
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

        if (extraMeat) {
            if (size == SandwichSize.SMALL) {
                total += 0.50;
            } else if (size == SandwichSize.MEDIUM) {
                total += 1.00;
            } else if (size == SandwichSize.LARGE) {
                total += 1.50;
            }
        }

        if (extraCheese) {
            if (size == SandwichSize.SMALL) {
                total += 0.30;
            } else if (size == SandwichSize.MEDIUM) {
                total += 0.60;
            } else if (size == SandwichSize.LARGE) {
                total += 0.90;
            }
        }
        return total;
    }

    @Override
    public String productDetails() {
        StringBuilder details = new StringBuilder(name + "    " + String.format("$%.2f", calculateProductTotal()) + " \n"
                + size + "\n    Bread Type: " + breadType + "\n    Toasted: " + isToasted +  "\n    Regular Toppings: ");
        for (RegularToppings topping : regularToppings) {
            details.append(topping).append(", ");
        }
        details.append("\n    Premium Toppings: ");
        for (PremiumToppings topping : premiumToppings) {
            details.append(topping).append(", ");
        }
        if(extraMeat) {
            details.append("\n    Extra Meat: Yes");
        } else {
            details.append("\n    Extra Meat: No");
        }
        details.append("\n    Sauces: ");
        for (Sauces sauce : sauces) {
            details.append(sauce).append(", ");
        }
        return details.toString();
    }

    public void addRegularTopping(RegularToppings topping) {
        regularToppings.add(topping);
    }

    public void addPremiumTopping(PremiumToppings topping) {
        premiumToppings.add(topping);
    }

    public void addSauce(Sauces sauce) {
        sauces.add(sauce);
    }

    public void setExtraMeat(boolean extraMeat) {
        this.extraMeat = extraMeat;
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }
}
