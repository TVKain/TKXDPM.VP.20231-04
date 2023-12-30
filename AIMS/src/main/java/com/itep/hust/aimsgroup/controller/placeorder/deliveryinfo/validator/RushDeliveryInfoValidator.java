package com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.validator;

import com.itep.hust.aimsgroup.model.cart.Cart;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RushDeliveryInfoValidator extends DeliveryInfoValidator {
    @Override
    public Pair<String, Boolean> validate(Map<String, String> deliveryInfoData) {
        if (!validateCommonFields(deliveryInfoData).getValue()) {
            return validateCommonFields(deliveryInfoData);
        }

        if (!validateRushInstruction(deliveryInfoData.get("rushInstruction"))) {
            return new Pair<>("Invalid rush instruction", false);
        }

        if (!validateRushTime(deliveryInfoData.get("rushTime"))) {
            return new Pair<>("Invalid rush time", false);
        }

        if (!validateRushCity(deliveryInfoData.get("city"))) {
            return new Pair<>("Can not place rush order - city not supported", false);
        }

        if (!validateRushMedias()) {
            return new Pair<>("Can not place rush order - no medias supported", false);
        }

        return new Pair<>("", true);
    }

    private boolean validateRushTime(String rushTime) {
        return rushTime.matches("\\d+");
    }

    private boolean validateRushInstruction(String rushInstruction) {
        return !rushInstruction.isBlank();
    }

    private boolean validateRushCity(String city) {
        List<String> supported = new ArrayList<>();
        supported.add("ha noi");
        return supported.contains(city.toLowerCase());
    }

    private boolean validateRushMedias() {
        return !Cart.getInstance().getRushSupportedMedias().isEmpty();
    }

}
