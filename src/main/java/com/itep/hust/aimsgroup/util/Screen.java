package com.itep.hust.aimsgroup.util;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Utility class responsible for setting the current screen
 * @author TVKain 
 */
public class Screen {
    private static Stage  mainStage = null;

    /**
     * Set up the main stage should only be called once in Application.java
     * @param mainStage
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

    }

    /**
     * witch the screen to the scene with the root obtain from fxmlURL
     * @param fxmlURL
     */
    public static void setScreen(String fxmlURL) {

    }

    /**
     * witch the screen to the scene with the root obtain from the argument
     * @param root          The root of the scene
     */
    public static void setScreen(Parent root) {

    }


}