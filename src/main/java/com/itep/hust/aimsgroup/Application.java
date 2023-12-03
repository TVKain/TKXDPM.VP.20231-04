package com.itep.hust.aimsgroup;

import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.AdminLoginViewHandler;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Screen.setMainStage(stage);

        stage.setTitle("Hello!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}