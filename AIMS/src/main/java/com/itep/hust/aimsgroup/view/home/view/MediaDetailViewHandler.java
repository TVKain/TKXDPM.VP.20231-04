package com.itep.hust.aimsgroup.view.home.view;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.media.book.Book;
import com.itep.hust.aimsgroup.model.media.cd.CD;
import com.itep.hust.aimsgroup.model.media.dvd.DVD;
import com.itep.hust.aimsgroup.util.ComponentLoader;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.home.HomeViewHandler;
import com.itep.hust.aimsgroup.view.home.view.book.HomeDetailBookViewHandler;
import com.itep.hust.aimsgroup.view.home.view.cd.HomeDetailCDViewHandler;
import com.itep.hust.aimsgroup.view.home.view.dvd.HomeDetailDVDViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class MediaDetailViewHandler implements Initializable {
    @FXML
    private ImageView mediaImage;
    @FXML
    private Label category;
    @FXML
    private Label id;
    @FXML
    private Label price;
    @FXML
    private Label quantity;
    @FXML
    private CheckBox rushDelivery;
    @FXML
    private Label title;
    @FXML
    private Label value;
    @FXML
    private VBox vbox;

    @FXML
    private Label weight;
    private Media media;
    public MediaDetailViewHandler(Media media) {
        this.media = media;
    }
    @FXML
    void ok(ActionEvent event) {
        Screen.setScreen("/fxml/home/home.fxml", new HomeViewHandler());
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
        rushDelivery.setSelected(media.isRushDelivery());
        try {
            mediaImage.setImage(new Image(getClass().getResource(media.getImageURL()).toURI().toString()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        if(media instanceof Book) {
            Book book = (Book) media;
            vbox.getChildren().add(ComponentLoader.getComponent("/fxml/home/view/book/view_detail_book.fxml", new HomeDetailBookViewHandler(book)));
        } else if(media instanceof DVD) {
            DVD dvd = (DVD) media;
            vbox.getChildren().add(ComponentLoader.getComponent("/fxml/home/view/dvd/view_detail_dvd.fxml", new HomeDetailDVDViewHandler(dvd)));
        } else if (media instanceof CD) {
            CD cd = (CD) media;
            vbox.getChildren().add(ComponentLoader.getComponent("/fxml/home/view/cd/view_detail_cd.fxml", new HomeDetailCDViewHandler(cd)));
        }
    }
}
