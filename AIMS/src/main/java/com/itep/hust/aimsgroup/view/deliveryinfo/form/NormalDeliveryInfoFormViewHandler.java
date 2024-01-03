package com.itep.hust.aimsgroup.view.deliveryinfo.form;

import com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.factory.DeliveryInfoFactory;
import com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.factory.NormalDeliveryInfoFactory;
import com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.validator.DeliveryInfoValidator;
import com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.validator.NormalDeliveryInfoValidator;
import com.itep.hust.aimsgroup.controller.placeorder.shippingcalculator.NormalShippingCalculatorStrategy;
import com.itep.hust.aimsgroup.controller.placeorder.shippingcalculator.ShippingCalculatorStrategy;
import javafx.fxml.FXML;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class NormalDeliveryInfoFormViewHandler extends  DeliveryInfoFormViewHandler {
    @FXML
    private void initialize() {
        initializeCityComboBox();
    }

    @Override
    public Pair<String, Boolean> validateValue() {
        DeliveryInfoValidator deliveryInfoValidator = new NormalDeliveryInfoValidator();
        return deliveryInfoValidator.validate(getFormValue());
    }

    @Override
    public Map<String, String> getFormValue() {
        String name = nameTextField.getText();
        String phone = phoneTextField.getText();
        String email = emailTextField.getText();
        String instruction = shippingInstructionTextField.getText();
        String city = cityComboBox.getValue();
        String address = addressTextField.getText();

        Map<String, String> formValue = new HashMap<>();

        formValue.put("name", name);
        formValue.put("phone", phone);
        formValue.put("instruction", instruction);
        formValue.put("city", city);
        formValue.put("address", address);
        formValue.put("email", email);

        return formValue;
    }

    @Override
    public DeliveryInfoFactory getDeliveryInfoFactory() {
        return new NormalDeliveryInfoFactory();
    }

    @Override
    public ShippingCalculatorStrategy getShippingFeeCalculator() {
        return new NormalShippingCalculatorStrategy();
    }
}
