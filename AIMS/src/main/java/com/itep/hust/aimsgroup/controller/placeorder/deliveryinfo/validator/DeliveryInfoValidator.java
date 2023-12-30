package com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.validator;


import javafx.util.Pair;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Map;

public abstract class DeliveryInfoValidator {
    public abstract Pair<String, Boolean> validate(Map<String, String> deliveryInfoData);

    protected final Pair<String, Boolean> validateCommonFields(Map<String, String> deliveryInfoData) {
        if (!validateName(deliveryInfoData.get("name"))) {
            return new Pair<>("Invalid name", false);
        }

        if (!validatePhone(deliveryInfoData.get("phone"))) {
            return new Pair<>("Invalid phone number", false);
        }

        if (!validateEmail(deliveryInfoData.get("email"))) {
            return new Pair<>("Invalid email", false);
        }

        if (!validateAddress(deliveryInfoData.get("address"))) {
            return new Pair<>("Invalid address", false);
        }

        if (!validateCity(deliveryInfoData.get("city"))) {
            return new Pair<>("Invalid city", false);
        }

        if (!validateInstruction(deliveryInfoData.get("instruction"))) {
            return new Pair<>("Invalid instruction", false);
        }

        return new Pair<>("", true);
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

    private boolean validateEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
