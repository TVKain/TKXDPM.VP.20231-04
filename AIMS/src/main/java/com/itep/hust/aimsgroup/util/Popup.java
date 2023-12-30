package com.itep.hust.aimsgroup.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

import java.util.Optional;



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
        getAlert(Alert.AlertType.ERROR, "Error", message).showAndWait();
    }

    /**
     * Shows a simple success popup with a message and a button to close
     * Prevents interaction with the main stage
     * @param message           The message to be displayed
     */
    public static void showSuccess(String message) {
        getAlert(Alert.AlertType.INFORMATION, "Success", message).showAndWait();
    }

    /**
     * Shows a confirmation dialog execute the callback based on user input
     * @param message           The message to be displayed
     * @param onOk              Runnable, runs if user click ok
     * @param onCancel          Runnable, runs if user click cancel
     */
    public static void showConfirmationDialog(String message, Runnable onOk, Runnable onCancel) {
        Alert alert = getAlert(Alert.AlertType.CONFIRMATION, "Confirm", message);
        Optional<ButtonType> alertValue = alert.showAndWait();
        ButtonType buttonType = alertValue.get();

        if (buttonType == ButtonType.OK && onOk != null) {
            onOk.run();
        } else if (buttonType == ButtonType.CANCEL && onCancel != null) {
            onCancel.run();
        }
    }

    /**
     * Helper private method to reuse code to set up alert
     * @param alertType         Type of the alert
     * @param title             Title of the alert
     * @param message           Message of the alert
     * @return alert            The alert object
     */
    private static Alert getAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.initOwner(null);
        alert.initModality(Modality.APPLICATION_MODAL);

        return alert;
    }
}
