package com.itep.hust.aimsgroup.view;

import com.itep.hust.aimsgroup.util.Screen;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginViewHandler {
    @FXML
    private Button adminButton;

    @FXML
    private Button customerButton;

    @FXML
    public void initialize() {
        adminButton.setOnMouseClicked(e -> {
            Screen.setScreen("/fxml/login-admin/login-admin.fxml", new AdminLoginViewHandler());
        });

        customerButton.setOnMouseClicked(e -> {
            Screen.setScreen("/fxml/home/home.fxml", new HomeViewHandler());
        });
    }
}
