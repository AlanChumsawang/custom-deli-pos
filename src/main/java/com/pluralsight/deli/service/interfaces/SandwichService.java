package com.pluralsight.deli.service.interfaces;

import com.pluralsight.deli.model.Sandwich;
import com.pluralsight.deli.model.enums.BreadType;
import com.pluralsight.deli.model.enums.SandwichSize;


public interface SandwichService {
    SandwichSize selectSandwichSize();
    BreadType selectBreadType();
    void selectRegularToppings(Sandwich sandwich);
    void selectPremiumToppings(Sandwich sandwich);
}