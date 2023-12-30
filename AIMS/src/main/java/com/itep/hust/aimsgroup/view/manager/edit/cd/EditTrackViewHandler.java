package com.itep.hust.aimsgroup.view.manager.edit.cd;

import com.itep.hust.aimsgroup.model.media.cd.Track;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTrackViewHandler implements Initializable {
    @FXML
    private HBox hboxTrack;

    @FXML
    private TextField newTrack;
    private Track track;

    public EditTrackViewHandler(Track track) {
        this.track = track;
    }

    @FXML
    void deleteTrack(ActionEvent event) {
        VBox vbox = (VBox) hboxTrack.getParent();
        vbox.getChildren().remove(hboxTrack);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newTrack.setText(track.getName());
    }
}
