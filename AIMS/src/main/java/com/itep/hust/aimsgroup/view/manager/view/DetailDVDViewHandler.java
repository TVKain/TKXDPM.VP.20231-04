package com.itep.hust.aimsgroup.view.manager.view;

import com.itep.hust.aimsgroup.model.media.dvd.DVD;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailDVDViewHandler implements Initializable {
    @FXML
    private Label director;

    @FXML
    private Label discType;

    @FXML
    private Label language;

    @FXML
    private Label runtime;

    @FXML
    private Label studio;

    @FXML
    private Label subtitle;
    private DVD dvd;
    public DetailDVDViewHandler(DVD dvd) {
        this.dvd = dvd;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        discType.setText(dvd.getDiscType());
        director.setText(dvd.getDirector());
        runtime.setText(dvd.getRuntime());
        studio.setText(dvd.getStudio());
        language.setText(dvd.getLanguage());
        subtitle.setText(dvd.getSubtitles());
    }
}
