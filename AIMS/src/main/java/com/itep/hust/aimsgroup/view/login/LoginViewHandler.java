package com.itep.hust.aimsgroup.view.login;

import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.home.HomeViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LoginViewHandler {

    @FXML
    private Button customerButton;

    @FXML
    private Button loginButton;


    @FXML
    public void initialize() {
        loginButton.setOnMouseClicked(e -> {
            Screen.setScreen("/fxml/login/login-account.fxml", new AccountLoginViewHandler());
        });

        customerButton.setOnMouseClicked(e -> {
            Screen.setScreen("/fxml/home/home.fxml", new HomeViewHandler());
        });
    }
}
