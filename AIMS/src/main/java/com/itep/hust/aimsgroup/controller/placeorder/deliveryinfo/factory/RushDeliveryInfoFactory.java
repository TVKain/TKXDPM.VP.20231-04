package com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.factory;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.deliveryinfo.RushDeliveryInfo;

import java.util.Map;

public class RushDeliveryInfoFactory implements DeliveryInfoFactory {
    @Override
    public DeliveryInfo getDeliveryInfo(Map<String, String> input) {
        RushDeliveryInfo rushDeliveryInfo = new RushDeliveryInfo();

        rushDeliveryInfo.setName(input.get("name"));
        rushDeliveryInfo.setAddress(input.get("address"));
        rushDeliveryInfo.setEmail(input.get("email"));
        rushDeliveryInfo.setCity(input.get("city"));
        rushDeliveryInfo.setInstruction(input.get("instruction"));
        rushDeliveryInfo.setPhone(input.get("phone"));
        rushDeliveryInfo.setRushInstruction(input.get("rushInstruction"));
        rushDeliveryInfo.setRushTime(Integer.valueOf(input.get("rushTime")));

        return rushDeliveryInfo;
    }
}
