package com.itep.hust.aimsgroup.controller.placeorder.validator;

import com.itep.hust.aimsgroup.util.Popup;

import java.util.Map;

public abstract class DeliveryInfoValidator {
    public abstract boolean validate(Map<String, String> deliveryInfoData);

    protected final boolean validateCommonFields(Map<String, String> deliveryInfoData) {
        if (!validateName(deliveryInfoData.get("name"))) {
            Popup.showError("Invalid name");
            return false;
        }

        if (!validatePhone(deliveryInfoData.get("phone"))) {
            Popup.showError("Invalid phone");
            return false;
        }

        if (!validateAddress(deliveryInfoData.get("address"))) {
            Popup.showError("Invalid address");
            return false;
        }

        if (!validateCity(deliveryInfoData.get("city"))) {
            Popup.showError("Invalid city");
            return false;
        }

        if (!validateInstruction(deliveryInfoData.get("instruction"))) {
            Popup.showError("Invalid instruction");
            return false;
        }

        return true;
    }

    private boolean validateInstruction(String instruction) {
        return true;
    }

    private boolean validateAddress(String address) {
        return !address.isBlank();
    }

    private boolean validateCity(String city) {
        return true;
    }

    private boolean validatePhone(String phone) {
        return !phone.isBlank();
    }

    private boolean validateName(String name) {
        return !name.isBlank();
    }
}
