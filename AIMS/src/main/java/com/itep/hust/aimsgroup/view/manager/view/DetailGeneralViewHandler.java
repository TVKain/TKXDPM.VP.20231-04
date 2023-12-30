package com.itep.hust.aimsgroup.view.manager.view;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.media.book.Book;
import com.itep.hust.aimsgroup.model.media.cd.CD;
import com.itep.hust.aimsgroup.model.media.dvd.DVD;
import com.itep.hust.aimsgroup.util.ComponentLoader;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import com.itep.hust.aimsgroup.view.manager.edit.book.EditBookViewHandler;
import com.itep.hust.aimsgroup.view.manager.edit.cd.EditCDViewHandler;
import com.itep.hust.aimsgroup.view.manager.edit.dvd.EditDVDViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailGeneralViewHandler implements Initializable {
    @FXML
    private Label category;

    @FXML
    private Label id;

    @FXML
    private Label imageURL;

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
    public DetailGeneralViewHandler(Media media) {
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

        if(media instanceof Book) {
            Book book = (Book) media;
            vbox.getChildren().add(ComponentLoader.getComponent("/fxml/manager/view/book/view_detail_book.fxml", new DetailBookViewHandler(book)));
        } else if(media instanceof DVD) {
            DVD dvd = (DVD) media;
            vbox.getChildren().add(ComponentLoader.getComponent("/fxml/manager/view/dvd/view_detail_dvd.fxml", new DetailDVDViewHandler(dvd)));
        } else if (media instanceof CD) {
            CD cd = (CD) media;
            vbox.getChildren().add(ComponentLoader.getComponent("/fxml/manager/view/cd/view_detail_book.fxml", new DetailCDViewHandler(cd)));
        }
    }
}
