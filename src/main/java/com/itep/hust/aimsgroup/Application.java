package com.itep.hust.aimsgroup;

import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.AdminLoginViewHandler;
<<<<<<< HEAD
import javafx.scene.image.Image;
=======
import com.itep.hust.aimsgroup.view.HomeViewHandler;
>>>>>>> 19b6156ec3781520ad9c17043d4c07000d573be1
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        Screen.setMainStage(stage);
        Screen.setScreen("/fxml/home/home.fxml", new HomeViewHandler());

        stage.setTitle("Hello!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}