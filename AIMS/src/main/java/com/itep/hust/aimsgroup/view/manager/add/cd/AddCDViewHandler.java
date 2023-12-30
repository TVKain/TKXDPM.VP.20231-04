package com.itep.hust.aimsgroup.view.manager.add.cd;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.media.cd.CD;
import com.itep.hust.aimsgroup.model.media.cd.Track;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.ComponentLoader;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import com.itep.hust.aimsgroup.view.manager.add.AddMediaViewHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddCDViewHandler extends AddMediaViewHandler implements Initializable {
    @FXML
    private TextField artist;

    @FXML
    private TextField categoryCD;

    @FXML
    private TextField musicType;

    @FXML
    private TextField recordLabel;
    @FXML
    private VBox boxTrack;

    @FXML
    void addNewTrack(ActionEvent event) {
        boxTrack.getChildren().add(ComponentLoader.getComponent("/fxml/manager/add/add_new_track.fxml", new AddNewTrackViewHandler()));
    }
    @Override
    public void add(Media media) {

        if(artist.getText().isEmpty() || musicType.getText().isEmpty()||recordLabel.getText().isEmpty()||categoryCD.getText().isEmpty()) {
            Popup.showError("Vui lòng nhập đủ thông tin CD");
        } else if (boxTrack.getChildren().size() < 1) {
            Popup.showError("Vui lòng nhập tối thiểu 1 track !");
        } else {
            CD newCD = new CD(media.getId(), media.getTitle(), media.getCategory(), media.getPrice(), media.getValue(), media.getQuantity(),
                    media.getWeight(), media.getImageURL(), artist.getText(), recordLabel.getText(), musicType.getText(), categoryCD.getText(), media.getRushDelivery());
            for( Node box : boxTrack.getChildren()) {
                for (Node node: ((HBox) box).getChildren()) {
                    if(node instanceof TextField) {
                        newCD.addTrack(new Track(((TextField) node).getText()));
                    }
                }
            }

            SqliteMediaDao sqliteMediaDao = new SqliteMediaDao();
            sqliteMediaDao.insert(newCD);
            Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boxTrack.getChildren().add(ComponentLoader.getComponent("/fxml/manager/add/add_new_track.fxml", new AddNewTrackViewHandler()));
    }
}
