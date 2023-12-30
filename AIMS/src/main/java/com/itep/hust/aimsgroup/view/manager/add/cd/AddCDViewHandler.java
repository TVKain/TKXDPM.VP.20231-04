package com.itep.hust.aimsgroup.view.manager.add.cd;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.view.manager.add.AddMediaViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddCDViewHandler extends AddMediaViewHandler {
    @FXML
    private TextField artist;

    @FXML
    private TextField categoryCD;

    @FXML
    private TextField musicType;

    @FXML
    private TextField recordLabel;

    @FXML
    void addNewTrack(ActionEvent event) {
        System.out.println("OK");
    }
    @Override
    public void add(Media media) {
        System.out.println("OK");
    }
}
