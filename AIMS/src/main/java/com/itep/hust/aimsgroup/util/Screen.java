package com.itep.hust.aimsgroup.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utility class responsible for setting the current screen
 * @author TVKain 
 */
public class Screen {
    private static Stage  mainStage = null;

    /**
     * Set up the main stage should only be called once in Application.java
     * @param mainStage     the main stage of the application
     */
    public static void setMainStage(Stage mainStage) {
        Screen.mainStage = mainStage;
    }

    /**
     * Switch the screen to the scene with the root obtain from fxmlURL and assign the controller to it
     * @param fxmlURL       path to FXML file
     * @param handler       handler to assign to the FXML file
     */
    public static void setScreen(String fxmlURL, Object handler) {
        try {
            FXMLLoader loader = new FXMLLoader(Screen.class.getResource(fxmlURL));
            loader.setController(handler);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Switch the screen to the scene with the root obtain from fxmlURL
     * @param fxmlURL       path to FXML file
     */
    public static void setScreen(String fxmlURL) {
        setScreen(fxmlURL, null);
    }

    /**
     * Switch the screen to the scene with the root obtain from the argument
     * @param root          The root of the scene
     */
    public static void setScreen(Parent root) {
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
    }
}