package com.itep.hust.aimsgroup.model.deliveryinfo.factory;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import java.util.Map;

public interface DeliveryInfoFactory {
    DeliveryInfo getDeliveryInfo(Map<String, String> input);
}
