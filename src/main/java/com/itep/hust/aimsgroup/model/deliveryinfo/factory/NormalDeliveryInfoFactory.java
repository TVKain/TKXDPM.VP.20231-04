package com.itep.hust.aimsgroup.model.deliveryinfo.factory;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;

import java.util.Map;

public class NormalDeliveryInfoFactory implements DeliveryInfoFactory {
    @Override
    public DeliveryInfo getDeliveryInfo(Map<String, String> input) {
        DeliveryInfo deliveryInfo = new DeliveryInfo();

        deliveryInfo.setName(input.get("name"));
        deliveryInfo.setAddress(input.get("address"));
        deliveryInfo.setCity(input.get("city"));
        deliveryInfo.setInstruction(input.get("instruction"));
        deliveryInfo.setPhone(input.get("phone"));

        return deliveryInfo;
    }
}
