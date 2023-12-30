package com.itep.hust.aimsgroup.view.manager.add;

import com.itep.hust.aimsgroup.model.media.book.Book;
import com.itep.hust.aimsgroup.model.media.dvd.DVD;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class AddDVDViewHandler {
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
    private TextField language;

    @FXML
    private TextField subtitle;

    @FXML
    private TextField title;

    @FXML
    private TextField value;

    @FXML
    private TextField weight;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void confirm(ActionEvent event) {
        if(id.getText().isEmpty() || title.getText().isEmpty() || category.getText().isEmpty() || price.getText().isEmpty() || value.getText().isEmpty()
                || weight.getText().isEmpty() || quantity.getText().isEmpty() || imageURL.getText().isEmpty() || discType.getText().isEmpty() || director.getText().isEmpty() ||
                runtime.getText().isEmpty() || studio.getText().isEmpty() || subtitle.getText().isEmpty()        ) {
            Popup.showError("Vui nhập nhập đủ trường thông tin !");
        } else {
            int checkRush = rushDelivery.isSelected() ? 1 : 0;
            DVD newDVD = new DVD(Integer.parseInt(id.getText()), title.getText(), category.getText(), Integer.parseInt(price.getText()), Integer.parseInt(value.getText()), Integer.parseInt(quantity.getText()),
                    Double.parseDouble(weight.getText()), imageURL.getText(), discType.getText(), director.getText(), runtime.getText(), studio.getText(), language.getText(),  subtitle.getText(), checkRush);
            SqliteMediaDao sqliteMediaDao = new SqliteMediaDao();
            sqliteMediaDao.insert(newDVD);
            Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
        }
    }
}
