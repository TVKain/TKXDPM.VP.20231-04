package com.itep.hust.aimsgroup.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ComponentLoader {

    /**
     * Load component from fxml file and assign view handler to it
     * @param fxmlURL       url of fxml file
     * @param handler       the view handler
     * @return
     */
    public static Parent getComponent(String fxmlURL, Object handler) {
        try {
            FXMLLoader loader = new FXMLLoader(Screen.class.getResource(fxmlURL));
            loader.setController(handler);
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
