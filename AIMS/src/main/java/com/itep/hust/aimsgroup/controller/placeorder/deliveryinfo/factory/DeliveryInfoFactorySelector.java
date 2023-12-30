package com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.factory;

import com.itep.hust.aimsgroup.model.deliveryinfo.type.DeliveryType;

import java.util.HashMap;
import java.util.Map;

public class DeliveryInfoFactorySelector {
    private static final Map<DeliveryType, DeliveryInfoFactory> deliveryInfoFactoryMap = new HashMap<>();

    static {
        deliveryInfoFactoryMap.put(DeliveryType.NORMAL, new NormalDeliveryInfoFactory());
        deliveryInfoFactoryMap.put(DeliveryType.RUSH, new RushDeliveryInfoFactory());
    }
    public static DeliveryInfoFactory getDeliveryInfoFactory(DeliveryType deliveryType) {
        return deliveryInfoFactoryMap.get(deliveryType);
    }
}
