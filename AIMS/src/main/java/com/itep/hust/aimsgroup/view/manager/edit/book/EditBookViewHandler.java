package com.itep.hust.aimsgroup.view.manager.edit.book;

import com.itep.hust.aimsgroup.controller.manager.ManagerController;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.media.book.Book;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import com.itep.hust.aimsgroup.view.manager.edit.EditMediaViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditBookViewHandler extends EditMediaViewHandler implements Initializable {
    @FXML
    private TextField author;

    @FXML
    private TextField category;

    @FXML
    private TextField coverType;
    @FXML
    private DatePicker publishDate;

    @FXML
    private TextField publisher;

    private Book book;

    public EditBookViewHandler(Book book) {
        this.book = book;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        author.setText(book.getAuthor());
        coverType.setText(book.getCoverType());
        publisher.setText(book.getPublisher());
        publishDate.setValue(book.getPublishDate());
    }

    @Override
    public void update(Media media) {
        if(author.getText().isEmpty() || coverType.getText().isEmpty() || publisher.getText().isEmpty()
                || publishDate.getValue() == null) {
            Popup.showError("Vui nhập nhập đủ trường thông tin !");
        } else {
            Book newBook = new Book(media.getId(), media.getTitle(), media.getCategory(), media.getPrice(), media.getValue(), media.getQuantity(),
                    media.getWeight(), media.getImageURL(), author.getText(), coverType.getText(), publisher.getText(), publishDate.getValue(), media.getRushDelivery());
            ManagerController managerController = new ManagerController(new SqliteMediaDao());
            if(managerController.updateMedia(newBook)) {
                Popup.showSuccess("Cập nhật thông tin thành công !");
                Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
            } else {
                Popup.showError("Thông tin không hợp lệ");
            }
        }
    }
}
