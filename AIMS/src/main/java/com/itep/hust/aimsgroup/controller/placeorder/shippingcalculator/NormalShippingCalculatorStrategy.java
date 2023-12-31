package com.itep.hust.aimsgroup.controller.placeorder.shippingcalculator;

import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.media.Media;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NormalShippingCalculatorStrategy implements ShippingCalculatorStrategy {

    @Override
    public double calculateShip(DeliveryInfo deliveryInfo) {
        int total = Cart.getInstance().getTotalPrice();

        double ret;
        if (total > 100_000) {
            return 0;
        }

        List<String> startCities = new ArrayList<>();
        startCities.add("ha noi");
        startCities.add("tp. hcm");

        double startPrice;
        double startWeight;
        if (startCities.contains(deliveryInfo.getCity().toLowerCase())) {
            startPrice = 22000;
            startWeight = 3;
        } else {
            startPrice = 30000;
            startWeight = 0.5;
        }

        Map.Entry<Media, Integer> heaviestMedia = Cart.getInstance().getHeaviestMedia();

        double totalWeight = heaviestMedia.getValue() * heaviestMedia.getKey().getWeight();
        double remainingWeight =  totalWeight - startWeight;

        if (remainingWeight < 0) {
            ret = totalWeight * startPrice / startWeight;
        } else {
            ret = startPrice + 5000 * remainingWeight;
        }

        return ret;
    }

}
