package com.itep.hust.aimsgroup;

import com.itep.hust.aimsgroup.util.Screen;

import com.itep.hust.aimsgroup.view.DeliveryInfoViewHandler;
import com.itep.hust.aimsgroup.view.HomeViewHandler;
import com.itep.hust.aimsgroup.view.LoginViewHandler;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Analyze cohesion
 * Temporal cohesion
 * Initialize Screen and Initialize stage in the same subcomponent
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Screen.setMainStage(stage);
        Screen.setScreen("/fxml/home/home.fxml", new HomeViewHandler());

        stage.setWidth(1280);
        stage.setHeight(640);
        stage.setResizable(false);
        stage.setTitle("AIMS");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}