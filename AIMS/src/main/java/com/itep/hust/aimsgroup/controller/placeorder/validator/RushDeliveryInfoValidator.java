package com.itep.hust.aimsgroup.controller.placeorder.validator;

import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.util.Popup;

import java.util.ArrayList;
import java.util.Map;

public class RushDeliveryInfoValidator extends DeliveryInfoValidator {
    @Override
    public boolean validate(Map<String, String> deliveryInfoData) {
        if (!validateCommonFields(deliveryInfoData)) {
            return false;
        }

        if (!validateRushInstruction(deliveryInfoData.get("rushInstruction"))) {
            Popup.showError("Invalid rush instruction");
            return false;
        }

        if (!validateRushTime(deliveryInfoData.get("rushTime"))) {
            Popup.showError("Invalid rush time");
            return false;
        }

        if (!validateRushCity(deliveryInfoData.get("city"))) {
            Popup.showError("Can not place rush order - city not supported");
            return false;
        }

        if (!validateRushMedias()) {
            Popup.showError("Can not place rush order - no media support rush order");
            return false;
        }

        return true;
    }

    private boolean validateRushInstruction(String rushInstruction) {
        return !rushInstruction.isBlank();
    }

    private boolean validateRushTime(String rushTime) {
        String regex = "\\d+";
        return rushTime.matches(regex);
    }

    private boolean validateRushCity(String city) {
        ArrayList<String> rushOrderCities = new ArrayList<>();
        rushOrderCities.add("ha noi");

        return rushOrderCities.contains(city.toLowerCase());
    }

    private boolean validateRushMedias() {
        return !Cart.getInstance().getRushSupportedMedias().isEmpty();
    }
}
