package com.itep.hust.aimsgroup.view.manager.add;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.util.ComponentLoader;
import com.itep.hust.aimsgroup.util.Popup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AddGerenalInfomation implements Initializable {
    @FXML
    private TextField category;
    @FXML
    private Button okButton;

    @FXML
    private TextField id;

    @FXML
    private TextField imageURL;

    @FXML
    private TextField price;

    @FXML
    private TextField quantity;

    @FXML
    private CheckBox rushDelivery;

    @FXML
    private TextField title;

    @FXML
    private TextField value;

    @FXML
    private VBox vbox;

    @FXML
    private TextField weight;
    @FXML
    private ComboBox<String> typeOfMedia;
    private AddMediaViewHandler addMediaViewHandler;

    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void confirm(ActionEvent event) {
        if(id.getText().isEmpty() || title.getText().isEmpty() || category.getText().isEmpty() || price.getText().isEmpty() || value.getText().isEmpty()
                || weight.getText().isEmpty() || quantity.getText().isEmpty() || imageURL.getText().isEmpty()) {
            Popup.showError("Vui lòng nhập đủ thông tin của media!");
        } else {
            int checkRush = rushDelivery.isSelected() ? 1 : 0;
            Media media = new Media(Integer.parseInt(id.getText()), title.getText(), category.getText(), Integer.parseInt(price.getText()), Integer.parseInt(value.getText()), Integer.parseInt(quantity.getText()),
                    Double.parseDouble(weight.getText()), imageURL.getText(),checkRush);
            addMediaViewHandler.add(media);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeOfMedia.getItems().addAll("Book", "CD", "DVD");
        typeOfMedia.setOnAction(e -> handleComboBoxSelection());
    }
    private void handleComboBoxSelection() {
        okButton.setDisable(false);
        // Thực hiện các hành động khi ComboBox được chọn ở đây
        String selectedType = typeOfMedia.getValue();
        if(selectedType=="Book") {
            this.addMediaViewHandler =  new AddBookViewHandler();
        } else if (selectedType=="DVD") {
            
        } else if (selectedType=="CD") {
            
        }
        vbox.getChildren().add(ComponentLoader.getComponent("/fxml/manager/add/add_new_book.fxml", addMediaViewHandler));
    }
}
