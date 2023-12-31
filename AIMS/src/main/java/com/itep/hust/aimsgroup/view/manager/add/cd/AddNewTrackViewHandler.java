package com.itep.hust.aimsgroup.view.manager.add.cd;

import com.itep.hust.aimsgroup.util.Popup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddNewTrackViewHandler {
    @FXML
    private TextField newTrack;
    @FXML
    private HBox hboxTrack;

    @FXML
    void deleteTrack(ActionEvent event) {
        VBox vbox = (VBox) hboxTrack.getParent();
        vbox.getChildren().remove(hboxTrack);
    }
}
