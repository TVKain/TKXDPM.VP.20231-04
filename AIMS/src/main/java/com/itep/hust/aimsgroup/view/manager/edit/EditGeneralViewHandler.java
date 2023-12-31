package com.itep.hust.aimsgroup.view.manager.edit;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.media.book.Book;
import com.itep.hust.aimsgroup.model.media.cd.CD;
import com.itep.hust.aimsgroup.model.media.dvd.DVD;
import com.itep.hust.aimsgroup.util.ComponentLoader;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.manager.ManagerViewHandler;
import com.itep.hust.aimsgroup.view.manager.edit.book.EditBookViewHandler;
import com.itep.hust.aimsgroup.view.manager.edit.cd.EditCDViewHandler;
import com.itep.hust.aimsgroup.view.manager.edit.dvd.EditDVDViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class EditGeneralViewHandler implements Initializable {
    @FXML
    private TextField category;

    @FXML
    private TextField id;

    @FXML
    private TextField imageURL;


    @FXML
    private TextField price;

    @FXML
    private TextField quantity;


    @FXML
    private TextField title;

    @FXML
    private TextField value;
    @FXML
    private CheckBox rushDelivery;

    @FXML
    private VBox vbox;

    @FXML
    private TextField weight;
    private Media media;
    private EditMediaViewHandler editMediaViewHandler;

    public EditGeneralViewHandler(Media media) {
        this.media = media;
    }

    @FXML
    void cancel(ActionEvent event) {
        Screen.setScreen("/fxml/manager/manager.fxml", new ManagerViewHandler());
    }

    @FXML
    void confirm(ActionEvent event) {
        if(id.getText().isEmpty() || title.getText().isEmpty() || category.getText().isEmpty() || price.getText().isEmpty() || value.getText().isEmpty()
                || weight.getText().isEmpty() || quantity.getText().isEmpty() || imageURL.getText().isEmpty()) {
            Popup.showError("Vui lòng nhập đủ thông tin media !");
        } else {
            int checkRush = rushDelivery.isSelected() ? 1 : 0;
            Media media = new Media(Integer.parseInt(id.getText()), title.getText(), category.getText(), Integer.parseInt(price.getText()), Integer.parseInt(value.getText()), Integer.parseInt(quantity.getText()),
                    Double.parseDouble(weight.getText()), imageURL.getText(),checkRush);
            editMediaViewHandler.update(media);
        }
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
            this.editMediaViewHandler = new EditBookViewHandler(book);
            vbox.getChildren().add(ComponentLoader.getComponent("/fxml/manager/edit/book/edit_book.fxml", editMediaViewHandler));
        } else if(media instanceof DVD) {
            DVD dvd = (DVD) media;
            this.editMediaViewHandler = new EditDVDViewHandler(dvd);
            vbox.getChildren().add(ComponentLoader.getComponent("/fxml/manager/edit/dvd/edit_dvd.fxml", editMediaViewHandler));
        } else if (media instanceof CD) {
            CD cd = (CD) media;
            this.editMediaViewHandler = new EditCDViewHandler(cd);
            vbox.getChildren().add(ComponentLoader.getComponent("/fxml/manager/edit/cd/edit_cd.fxml", editMediaViewHandler));
        }
    }
}
