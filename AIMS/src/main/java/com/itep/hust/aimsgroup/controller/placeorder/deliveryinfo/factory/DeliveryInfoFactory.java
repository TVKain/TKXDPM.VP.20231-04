package com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.factory;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import java.util.Map;

public interface DeliveryInfoFactory {
    DeliveryInfo getDeliveryInfo(Map<String, String> input);
}
