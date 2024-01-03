package com.itep.hust.aimsgroup.controller.placeorder.shippingcalculator;

import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.media.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ShippingCalculatorStrategy {
    double calculateShip(DeliveryInfo deliveryInfo);
}
