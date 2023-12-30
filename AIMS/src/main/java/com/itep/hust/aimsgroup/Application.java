package com.itep.hust.aimsgroup;

import com.itep.hust.aimsgroup.service.database.SqliteDatabase;
import com.itep.hust.aimsgroup.util.Screen;


import com.itep.hust.aimsgroup.view.manager.add.AddGerenalInfomation;
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
        SqliteDatabase.seed();
        Screen.setMainStage(stage);


//        Screen.setScreen("/fxml/delivery-info/delivery-info.fxml", new DeliveryInfoViewHandler());
        // Screen.setScreen("/fxml/login/login.fxml", new LoginViewHandler());
        Screen.setScreen("/fxml/manager/add/add_media.fxml", new AddGerenalInfomation());

        stage.setWidth(1340);
        stage.setHeight(760);
        stage.setResizable(true);

        stage.setTitle("AIMS");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}