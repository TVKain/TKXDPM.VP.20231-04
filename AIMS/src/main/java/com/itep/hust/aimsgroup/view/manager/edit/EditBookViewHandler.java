package com.itep.hust.aimsgroup.view.manager.edit;

import com.itep.hust.aimsgroup.model.media.book.Book;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditBookViewHandler implements Initializable {
    @FXML
    private TextField author;

    @FXML
    private TextField category;

    @FXML
    private TextField coverType;

    @FXML
    private TextField id;

    @FXML
    private TextField imageURL;

    @FXML
    private TextField price;

    @FXML
    private DatePicker publishDate;

    @FXML
    private TextField publisher;

    @FXML
    private TextField quantity;

    @FXML
    private CheckBox rushDelivery;

    @FXML
    private TextField title;

    @FXML
    private TextField value;

    @FXML
    private TextField weight;
    private Book book;

    public EditBookViewHandler(Book book) {
        this.book = book;
    }

    @FXML
    void cancel(ActionEvent event) {
        Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
    }

    @FXML
    void confirm(ActionEvent event) {
        if(title.getText().isEmpty() || category.getText().isEmpty() || price.getText().isEmpty() || value.getText().isEmpty()
                || weight.getText().isEmpty() || quantity.getText().isEmpty() || imageURL.getText().isEmpty() || author.getText().isEmpty() || coverType.getText().isEmpty() || publisher.getText().isEmpty()
                || publishDate.getValue() == null
        ) {
            Popup.showError("Vui nhập nhập đủ trường thông tin !");
        } else {
            int checkRush = rushDelivery.isSelected() ? 1 : 0;
            Book newBook = new Book(Integer.parseInt(id.getText()), title.getText(), category.getText(), Integer.parseInt(price.getText()), Integer.parseInt(value.getText()), Integer.parseInt(quantity.getText()),
                    Double.parseDouble(weight.getText()), imageURL.getText(), author.getText(), coverType.getText(), publisher.getText(), publishDate.getValue(), checkRush);
            SqliteMediaDao sqliteMediaDao = new SqliteMediaDao();
            sqliteMediaDao.update(newBook);
            Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(book.getId() + "");
        title.setText(book.getTitle());
        category.setText(book.getCategory());
        price.setText(book.getPrice()+"");
        value.setText(book.getValue()+"");
        weight.setText(book.getWeight()+"");
        quantity.setText(book.getQuantity()+"");
        imageURL.setText(book.getImageURL());
        rushDelivery.setSelected(book.isRushDelivery());
        author.setText(book.getAuthor());
        coverType.setText(book.getCoverType());
        publisher.setText(book.getPublisher());
        publishDate.setValue(book.getPublishDate());
    }
}
