package com.itep.hust.aimsgroup.controller.placeorder;

import com.itep.hust.aimsgroup.controller.placeorder.validator.DeliveryInfoValidator;
import com.itep.hust.aimsgroup.controller.placeorder.validator.DeliveryInfoValidatorSelector;
import com.itep.hust.aimsgroup.controller.placeorder.validator.NormalDeliveryInfoValidator;
import com.itep.hust.aimsgroup.controller.placeorder.validator.RushDeliveryInfoValidator;
import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.deliveryinfo.factory.DeliveryInfoFactorySelector;
import com.itep.hust.aimsgroup.model.deliveryinfo.type.DeliveryType;

import java.util.Map;

public class PlaceOrderController {
    private DeliveryInfoValidator deliveryInfoValidator;

    private DeliveryInfo deliveryInfo;
    public void setDeliveryInfoValidator(DeliveryType deliveryType) {
        this.deliveryInfoValidator = DeliveryInfoValidatorSelector.getDeliveryInfoValidator(deliveryType);
    }

    public void setDeliveryInfo(DeliveryType deliveryType, Map<String, String> deliveryInfoData) {
        this.deliveryInfo = DeliveryInfoFactorySelector
                .getDeliveryInfoFactory(deliveryType)
                .getDeliveryInfo(deliveryInfoData);
    }

    public boolean validateDeliveryInfo(Map<String, String> deliveryInfoData) {
        return deliveryInfoValidator.validate(deliveryInfoData);
    }
}
