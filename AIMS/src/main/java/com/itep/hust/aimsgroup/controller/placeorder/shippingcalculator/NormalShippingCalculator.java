package com.itep.hust.aimsgroup.controller.placeorder.shippingcalculator;

public class NormalShippingCalculator extends ShippingCalculator {
    @Override
    public double recalculateShip(double ship) {
        return ship;
    }
}
