package com.itep.hust.aimsgroup.view.manager.add.dvd;

import com.itep.hust.aimsgroup.controller.manager.ManagerController;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.media.dvd.DVD;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import com.itep.hust.aimsgroup.view.manager.add.AddMediaViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddDVDViewHandler extends AddMediaViewHandler {
    @FXML
    private TextField director;

    @FXML
    private TextField discType;

    @FXML
    private TextField language;

    @FXML
    private TextField runtime;

    @FXML
    private TextField studio;

    @FXML
    private TextField subtitle;

    @Override
    public void add(Media media) {
        if(discType.getText().isEmpty() || director.getText().isEmpty() ||
                runtime.getText().isEmpty() || studio.getText().isEmpty() || subtitle.getText().isEmpty()) {
            Popup.showError("Vui nhập nhập đủ trường thông tin DVD !");
        } else {
            DVD newDVD = new DVD(media.getId(), media.getTitle(), media.getCategory(), media.getPrice(), media.getValue(), media.getQuantity(),
                    media.getWeight(), media.getImageURL(), discType.getText(), director.getText(), runtime.getText(), studio.getText(), language.getText(),  subtitle.getText(), media.getRushDelivery());
            ManagerController managerController = new ManagerController(new SqliteMediaDao());
            managerController.addMedia(newDVD);
            Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
        }
    }
}
