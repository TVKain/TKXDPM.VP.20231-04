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
    private Label category;

    @FXML
    private Label director;

    @FXML
    private Label discType;

    @FXML
    private Label id;

    @FXML
    private Label imageURL;

    @FXML
    private Label language;

    @FXML
    private Label price;

    @FXML
    private Label quantity;

    @FXML
    private Label runtime;

    @FXML
    private CheckBox rushDelivery;

    @FXML
    private Label studio;

    @FXML
    private Label subtitle;

    @FXML
    private Label title;

    @FXML
    private Label value;

    @FXML
    private Label weight;
    private DVD dvd;

    @FXML
    void Ok(ActionEvent event) {
        Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
    }

    public DetailDVDViewHandler(DVD dvd) {
        this.dvd = dvd;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(dvd.getId() + "");
        title.setText(dvd.getTitle());
        category.setText(dvd.getCategory());
        price.setText(dvd.getPrice()+"");
        value.setText(dvd.getValue()+"");
        weight.setText(dvd.getWeight()+"");
        quantity.setText(dvd.getQuantity()+"");
        imageURL.setText(dvd.getImageURL());
        rushDelivery.setSelected(dvd.isRushDelivery());
        discType.setText(dvd.getDiscType());
        director.setText(dvd.getDirector());
        runtime.setText(dvd.getRuntime());
        studio.setText(dvd.getStudio());
        language.setText(dvd.getLanguage());
        subtitle.setText(dvd.getSubtitles());
    }
}
