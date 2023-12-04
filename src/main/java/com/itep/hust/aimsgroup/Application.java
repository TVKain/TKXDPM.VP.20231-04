package com.itep.hust.aimsgroup;

import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.AdminLoginViewHandler;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Screen.setMainStage(stage);
        Screen.setScreen("/fxml/login-admin/login-admin.fxml", new AdminLoginViewHandler());

        stage.setTitle("Hello!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}