package com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.validator;

import javafx.util.Pair;

import java.util.Map;

public class NormalDeliveryInfoValidator extends DeliveryInfoValidator {
    @Override
    public Pair<String, Boolean> validate(Map<String, String> deliveryInfoData) {
        return validateCommonFields(deliveryInfoData);
    }
}
