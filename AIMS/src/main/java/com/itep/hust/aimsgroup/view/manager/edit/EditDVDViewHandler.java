package com.itep.hust.aimsgroup.view.manager.edit;

import com.itep.hust.aimsgroup.model.media.dvd.DVD;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDVDViewHandler implements Initializable {
    @FXML
    private TextField category;

    @FXML
    private TextField director;

    @FXML
    private TextField discType;

    @FXML
    private TextField id;

    @FXML
    private TextField imageURL;

    @FXML
    private TextField language;

    @FXML
    private TextField price;

    @FXML
    private TextField quantity;

    @FXML
    private TextField runtime;

    @FXML
    private CheckBox rushDelivery;

    @FXML
    private TextField studio;

    @FXML
    private TextField subtitle;

    @FXML
    private TextField title;

    @FXML
    private TextField value;

    @FXML
    private TextField weight;
    private DVD dvd;

    public EditDVDViewHandler(DVD dvd) {
        this.dvd = dvd;
    }

    @FXML
    void cancel(ActionEvent event) {
        Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
    }

    @FXML
    void confirm(ActionEvent event) {
        if(title.getText().isEmpty() || category.getText().isEmpty() || price.getText().isEmpty() || value.getText().isEmpty()
                || weight.getText().isEmpty() || quantity.getText().isEmpty() || imageURL.getText().isEmpty() || discType.getText().isEmpty() || director.getText().isEmpty() ||
                runtime.getText().isEmpty() || studio.getText().isEmpty() || subtitle.getText().isEmpty()        ) {
            Popup.showError("Vui nhập nhập đủ trường thông tin !");
        } else {
            int checkRush = rushDelivery.isSelected() ? 1 : 0;
            DVD newDVD = new DVD(Integer.parseInt(id.getText()), title.getText(), category.getText(), Integer.parseInt(price.getText()), Integer.parseInt(value.getText()), Integer.parseInt(quantity.getText()),
                    Double.parseDouble(weight.getText()), imageURL.getText(), discType.getText(), director.getText(), runtime.getText(), studio.getText(), language.getText(),  subtitle.getText(), checkRush);
            SqliteMediaDao sqliteMediaDao = new SqliteMediaDao();
            sqliteMediaDao.update(newDVD);
            Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
        }
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
