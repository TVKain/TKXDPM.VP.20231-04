package com.itep.hust.aimsgroup.controller.placeorder.shippingcalculator;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;

public class ShippingCalculator {
    private ShippingCalculatorStrategy shippingCalculatorStrategy;

    public void setShippingCalculatorStrategy(ShippingCalculatorStrategy shippingCalculatorStrategy) {
        this.shippingCalculatorStrategy = shippingCalculatorStrategy;
    }

    public double execute(DeliveryInfo deliveryInfo) {
        return shippingCalculatorStrategy.calculateShip(deliveryInfo);
    }
}
