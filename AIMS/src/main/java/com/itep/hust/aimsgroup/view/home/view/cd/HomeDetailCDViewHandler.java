package com.itep.hust.aimsgroup.view.home.view.cd;

import com.itep.hust.aimsgroup.model.media.cd.CD;
import com.itep.hust.aimsgroup.model.media.cd.Track;
import com.itep.hust.aimsgroup.util.ComponentLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailCDViewHandler implements Initializable {
    @FXML
    private Label artist;

    @FXML
    private VBox boxTrack;

    @FXML
    private Label categoryCD;

    @FXML
    private Label musicType;

    @FXML
    private Label recordLabel;
    private CD cd;

    public DetailCDViewHandler(CD cd) {
        this.cd = cd;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        artist.setText(cd.getArtist());
        musicType.setText(cd.getMusicType());
        recordLabel.setText(cd.getRecordLabel());
        categoryCD.setText(cd.getCDCategory());

        for(Track track: cd.getListTrack()) {
            HBox hbox = (HBox) ComponentLoader.getComponent("/fxml/manager/view/cd/view_detail_track.fxml", new DetailTrackViewHandler(track));
            boxTrack.getChildren().add(hbox);
        }
    }
}
