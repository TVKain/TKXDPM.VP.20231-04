package com.itep.hust.aimsgroup.util;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 * Utility class responsible for displaying Popup
 * @author TVKain
 */
public class Popup {
    /**
     * Shows a simple error popup with a message and a button to close
     * Prevents interaction with the main stage
     * @param message           The message to be displayed
     */
    public static void showError(String message) {
        showAlert(Alert.AlertType.ERROR, "Error", message);
    }

    /**
     * Shows a simple success popup with a message and a button to close
     * Prevents interaction with the main stage
     * @param message           The message to be displayed
     */
    public static void showSuccess(String message) {
        showAlert(Alert.AlertType.INFORMATION, "Success", message);
    }

    /**
     * Helper private method to reuse code to show popup
     * @param alertType         Type of the alert
     * @param title             Title of the alert
     * @param message           Message of the alert
     */
    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.initOwner(null);
        alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait();
    }
}
