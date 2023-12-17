package com.itep.hust.aimsgroup.model.deliveryinfo.factory;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.deliveryinfo.type.DeliveryType;

import java.util.Map;

public abstract class DeliveryInfoFactory {
    public static DeliveryInfoFactory getDeliveryInfoFactory(DeliveryType deliveryType) {
        return switch (deliveryType) {
            case NORMAL -> new NormalDeliveryInfoFactory();
            case RUSH -> new RushDeliveryInfoFactory();
        };
    }
    public abstract DeliveryInfo getDeliveryInfo(Map<String, String> input);
}
