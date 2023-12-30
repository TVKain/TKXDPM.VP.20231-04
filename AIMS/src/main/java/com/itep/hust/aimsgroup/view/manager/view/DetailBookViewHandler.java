package com.itep.hust.aimsgroup.view.manager.view;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.media.book.Book;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailBookViewHandler implements Initializable {
    @FXML
    private Label author;

    @FXML
    private Label category;

    @FXML
    private Label id;

    @FXML
    private Label imageURL;

    @FXML
    private Label price;

    @FXML
    private Label publishDate;

    @FXML
    private Label publisher;

    @FXML
    private Label quantity;

    @FXML
    private CheckBox rushDelivery;

    @FXML
    private Label title;

    @FXML
    private Label type;

    @FXML
    private Label value;

    @FXML
    private Label weight;
    private Book book;

    public DetailBookViewHandler(Book book) {
        this.book = book;
    }

    @FXML
    void ok(ActionEvent event) {
        Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
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
        type.setText(book.getCoverType());
        publisher.setText(book.getPublisher());
        publishDate.setText(book.getPublishDate().toString());
    }
}
