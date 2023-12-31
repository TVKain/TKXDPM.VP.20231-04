package com.itep.hust.aimsgroup.view.deliveryinfo.form;

import com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.factory.DeliveryInfoFactory;
import com.itep.hust.aimsgroup.controller.placeorder.deliveryinfo.validator.DeliveryInfoValidator;
import com.itep.hust.aimsgroup.controller.placeorder.shippingcalculator.ShippingCalculator;
import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.util.Popup;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.util.Map;

public abstract class DeliveryInfoFormViewHandler {
    @FXML
    protected ComboBox<String> cityComboBox;

    @FXML
    protected TextField nameTextField;

    @FXML
    protected TextField addressTextField;

    @FXML
    protected TextField emailTextField;

    @FXML
    protected TextField phoneTextField;

    @FXML
    protected TextField shippingInstructionTextField;



    protected void initializeCityComboBox() {
        cityComboBox.getItems().addAll("Ha Noi", "Bac Ninh", "TP. HCM", "Da Nang");
        cityComboBox.getSelectionModel().selectFirst();
    }

    public abstract Pair<String, Boolean> validateValue();
    public abstract Map<String, String> getFormValue();

    public abstract DeliveryInfoFactory getDeliveryInfoFactory();

    public abstract ShippingCalculator getShippingFeeCalculator();
}
