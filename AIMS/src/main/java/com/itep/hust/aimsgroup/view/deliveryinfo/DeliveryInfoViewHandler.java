package com.itep.hust.aimsgroup.view.deliveryinfo;

import com.itep.hust.aimsgroup.controller.placeorder.PlaceOrderController;
import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.deliveryinfo.type.DeliveryType;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.util.ComponentLoader;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.CartViewHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DeliveryInfoViewHandler {
    @FXML
    ComboBox<DeliveryType> deliveryTypeComboBox;

    @FXML
    Pane formContainer;

    @FXML
    public void initialize() {
        initializeFormContainer();
        initializeDeliveryTypeComboBox();
    }

    public void initializeFormContainer() {
        formContainer.getChildren().add(ComponentLoader.getComponent("/fxml/delivery-info/delivery-info-form.fxml", new Object()));
    }


    private void initializeDeliveryTypeComboBox() {
        deliveryTypeComboBox.getItems().addAll(DeliveryType.values());
        deliveryTypeComboBox.getSelectionModel().select(DeliveryType.NORMAL);

        deliveryTypeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCurrentForm(newValue);
        });
    }

    private void setCurrentForm(DeliveryType deliveryType) {
        formContainer.getChildren().clear();

        Parent form = switch (deliveryType) {
            case NORMAL -> ComponentLoader.getComponent("/fxml/delivery-info/delivery-info-form.fxml", new Object());
            case RUSH -> ComponentLoader.getComponent("/fxml/delivery-info/rush-delivery-info-form.fxml", new Object());
        };

        formContainer.getChildren().add(form);
    }
}
