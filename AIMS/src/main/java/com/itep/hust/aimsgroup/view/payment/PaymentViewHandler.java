package com.itep.hust.aimsgroup.view.payment;

import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.home.HomeViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PaymentViewHandler {
    @FXML
    private Button backToHomeButton;

    @FXML
    private void initialize() {
        backToHomeButton.setOnMouseClicked(e -> {

            Screen.setScreen("/fxml/home/home.fxml", new HomeViewHandler());
        });
    }
}
