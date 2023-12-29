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
    private Media media;

    public DetailBookViewHandler(Media media) {
        this.media = media;
    }

    @FXML
    void ok(ActionEvent event) {
        Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setText(media.getId() + "");
        title.setText(media.getTitle());
        category.setText(media.getCategory());
        price.setText(media.getPrice()+"");
        value.setText(media.getValue()+"");
        weight.setText(media.getWeight()+"");
        quantity.setText(media.getQuantity()+"");
        imageURL.setText(media.getImageURL());
        rushDelivery.setSelected(media.isRushDelivery());
        Book book = (Book) media;
        author.setText(book.getAuthor());
        type.setText(book.getCoverType());
        publisher.setText(((Book) media).getPublisher());
        publishDate.setText(book.getPublishDate().toString());
    }
}
