package com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.validator;

import com.itep.hust.aimsgroup.model.deliveryinfo.type.DeliveryType;

import java.util.HashMap;
import java.util.Map;

public class DeliveryInfoValidatorSelector {
    private static final Map<DeliveryType, DeliveryInfoValidator> deliveryInfoValidatorMap = new HashMap<>();

    static {
        deliveryInfoValidatorMap.put(DeliveryType.NORMAL, new NormalDeliveryInfoValidator());
        deliveryInfoValidatorMap.put(DeliveryType.RUSH, new RushDeliveryInfoValidator());
    }

    public static DeliveryInfoValidator getDeliveryInfoValidator(DeliveryType deliveryType) {
        return deliveryInfoValidatorMap.get(deliveryType);
    }
}
