package com.itep.hust.aimsgroup.controller.placeorder.validator;

import java.util.Map;

public class NormalDeliveryInfoValidator extends DeliveryInfoValidator {
    @Override
    public boolean validate(Map<String, String> deliveryInfoData) {
        return validateCommonFields(deliveryInfoData);
    }
}
