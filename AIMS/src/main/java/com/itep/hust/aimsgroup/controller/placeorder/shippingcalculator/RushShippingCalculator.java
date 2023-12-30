package com.itep.hust.aimsgroup.controller.placeorder.shippingcalculator;

import com.itep.hust.aimsgroup.model.cart.Cart;

public class RushShippingCalculator extends ShippingCalculator {
    @Override
    public double recalculateShip(double ship) {
        int rushCount = Cart.getInstance().getRushSupportedMediasCount();

        return ship + rushCount * 10000;
    }
}
