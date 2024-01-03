package com.itep.hust.aimsgroup.view.deliveryinfo.form;

import com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.factory.DeliveryInfoFactory;
import com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.factory.RushDeliveryInfoFactory;
import com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.validator.DeliveryInfoValidator;
import com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.validator.RushDeliveryInfoValidator;
import com.itep.hust.aimsgroup.controller.placeorder.shippingcalculator.RushShippingCalculatorStrategy;
import com.itep.hust.aimsgroup.controller.placeorder.shippingcalculator.ShippingCalculatorStrategy;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;

public class RushDeliveryInfoFormViewHandler extends DeliveryInfoFormViewHandler {
    @FXML
    TextField rushInstructionTextField;

    @FXML
    TextField rushTimeTextField;

    @FXML
    private void initialize() {
        initializeCityComboBox();
    }

    @Override
    public Pair<String, Boolean> validateValue() {
        DeliveryInfoValidator deliveryInfoValidator = new RushDeliveryInfoValidator();
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
        String rushInstruction = rushInstructionTextField.getText();
        String rushTime = rushTimeTextField.getText();

        Map<String, String> formValue = new HashMap<>();

        formValue.put("name", name);
        formValue.put("phone", phone);
        formValue.put("instruction", instruction);
        formValue.put("city", city);
        formValue.put("address", address);
        formValue.put("rushInstruction", rushInstruction);
        formValue.put("rushTime", rushTime);
        formValue.put("email", email);

        return formValue;
    }

    @Override
    public DeliveryInfoFactory getDeliveryInfoFactory() {
        return new RushDeliveryInfoFactory();
    }

    @Override
    public ShippingCalculatorStrategy getShippingFeeCalculator() {
        return new RushShippingCalculatorStrategy();
    }
}
