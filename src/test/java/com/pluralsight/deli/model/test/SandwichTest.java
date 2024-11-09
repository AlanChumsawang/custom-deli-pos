package com.pluralsight.deli.model.test;

import com.pluralsight.deli.model.Sandwich;
import com.pluralsight.deli.model.enums.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SandwichTest {
    private Sandwich sandwich;

    @BeforeEach
    void setup() {
        sandwich = new Sandwich("Sandwich", SandwichSize.SMALL, BreadType.WHITE, true, false);
    }

    @Test
    void testConstructor() {
        assert(sandwich.getName().equals("Sandwich"));
        assert(sandwich.getSize().equals(SandwichSize.SMALL));
        assert(sandwich.getBreadType().equals(BreadType.WHITE));
        assert(sandwich.getRegularToppings().isEmpty());
        assert(sandwich.getPremiumToppings().isEmpty());
        assert(sandwich.isToasted());
        assert(!sandwich.isSignature());
    }

    @Test
    void testAddRegularTopping() {
        sandwich.addRegularTopping(RegularToppings.LETTUCE);
        assert(sandwich.getRegularToppings().size() == 1);
        assert(sandwich.getRegularToppings().get(0).equals(RegularToppings.LETTUCE));
    }

    @Test
    void testAddPremiumTopping() {
        sandwich.addPremiumTopping(PremiumToppings.BACON);
        assert(sandwich.getPremiumToppings().size() == 1);
        assert(sandwich.getPremiumToppings().get(0).equals(PremiumToppings.BACON));
    }

    @Test
    void testCalculateProductTotal() {
        sandwich.addRegularTopping(RegularToppings.LETTUCE);
        sandwich.addPremiumTopping(PremiumToppings.BACON);
        sandwich.addPremiumTopping(PremiumToppings.AMERICAN_CHEESE);
        assert(sandwich.calculateProductTotal() == 7.25);
    }





}
