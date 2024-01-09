package com.itep.hust.aimsgroup.view.home.view.cd;

import com.itep.hust.aimsgroup.model.media.cd.Track;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailTrackViewHandler implements Initializable {
    @FXML
    private HBox hboxTrack;

    @FXML
    private Label nameTrack;
    private Track track;
    public DetailTrackViewHandler(Track track) {
        this.track = track;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTrack.setText(track.getName());
    }

}
