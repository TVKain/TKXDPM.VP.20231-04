package com.itep.hust.aimsgroup.view.manager.add.book;

import com.itep.hust.aimsgroup.controller.manager.ManagerController;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.media.book.Book;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import com.itep.hust.aimsgroup.view.manager.add.AddMediaViewHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddBookViewHandler extends AddMediaViewHandler {
    @FXML
    private TextField author;

    @FXML
    private TextField coverType;

    @FXML
    private DatePicker publishDate;

    @FXML
    private TextField publisher;

    @Override
    public void add(Media media) {
        if(author.getText().isEmpty() || coverType.getText().isEmpty() || publisher.getText().isEmpty()
                || publishDate.getValue() == null) {
            Popup.showError("Vui nhập nhập đủ trường thông tin của sách !");
        } else {
            Book newBook = new Book(media.getId(), media.getTitle(), media.getCategory(), media.getPrice(), media.getValue(), media.getQuantity(),
                    media.getWeight(), media.getImageURL(), author.getText(), coverType.getText(), publisher.getText(), publishDate.getValue(), media.getRushDelivery());
            ManagerController managerController = new ManagerController(new SqliteMediaDao());
            if(managerController.addMedia(newBook)) {
                Popup.showSuccess("Thêm mới book thành công !");
                Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
            } else {
                Popup.showError("Thêm mới thất bại! Thông tin không hợp lệ");
            }
        }
    }
}
