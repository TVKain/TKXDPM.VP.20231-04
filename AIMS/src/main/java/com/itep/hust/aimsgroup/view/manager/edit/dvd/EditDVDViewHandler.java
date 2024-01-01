package com.itep.hust.aimsgroup.view.manager.edit.dvd;

import com.itep.hust.aimsgroup.controller.manager.ManagerController;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.media.dvd.DVD;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import com.itep.hust.aimsgroup.view.manager.edit.EditMediaViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditDVDViewHandler extends EditMediaViewHandler implements Initializable {
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
    private DVD dvd;

    public EditDVDViewHandler(DVD dvd) {
        this.dvd = dvd;

    }

    @FXML
    void cancel(ActionEvent event) {
        Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
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

    @Override
    public void update(Media media) {
        if( discType.getText().isEmpty() || director.getText().isEmpty() ||
                runtime.getText().isEmpty() || studio.getText().isEmpty() || subtitle.getText().isEmpty()        ) {
            Popup.showError("Vui nhập nhập đủ trường thông tin !");
        } else {
            DVD newDVD = new DVD(media.getId(), media.getTitle(), media.getCategory(), media.getPrice(), media.getValue(), media.getQuantity(),
                    media.getWeight(), media.getImageURL(), discType.getText(), director.getText(), runtime.getText(), studio.getText(), language.getText(),  subtitle.getText(), media.getRushDelivery());
//            SqliteMediaDao sqliteMediaDao = new SqliteMediaDao();
//            sqliteMediaDao.update(newDVD);
            ManagerController managerController = new ManagerController(new SqliteMediaDao());
            if(managerController.updateMedia(newDVD)) {
                Popup.showSuccess("Cập nhật thông tin thành công !");
                Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
            } else {
                Popup.showError("Thông tin không hợp lệ");
            }
        }
    }
}
