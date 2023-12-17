package com.itep.hust.aimsgroup.controller.placeorder;

import com.itep.hust.aimsgroup.controller.placeorder.validator.DeliveryInfoValidator;
import com.itep.hust.aimsgroup.controller.placeorder.validator.NormalDeliveryInfoValidator;
import com.itep.hust.aimsgroup.controller.placeorder.validator.RushDeliveryInfoValidator;
import com.itep.hust.aimsgroup.model.deliveryinfo.type.DeliveryType;

import java.util.Map;

public class PlaceOrderController {
    private DeliveryInfoValidator deliveryInfoValidator;

    public void setDeliveryInfoValidator(DeliveryType deliveryType) {
        this.deliveryInfoValidator = switch (deliveryType) {
            case NORMAL -> new NormalDeliveryInfoValidator();
            case RUSH -> new RushDeliveryInfoValidator();
        };
    }

    public boolean validateDeliveryInfo(Map<String, String> deliveryInfoData) {
        return deliveryInfoValidator.validate(deliveryInfoData);
    }
}
