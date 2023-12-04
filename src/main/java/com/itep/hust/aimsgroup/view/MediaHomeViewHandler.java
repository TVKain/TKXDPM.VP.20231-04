package com.itep.hust.aimsgroup.view;

import com.itep.hust.aimsgroup.model.media.Media;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;

public class MediaHomeViewHandler {

    @FXML
    private Button addToCartBtn;

    @FXML
    private Label mediaAvail;

    @FXML
    private ImageView mediaImage;

    @FXML
    private Label mediaPrice;

    @FXML
    private Label mediaTitle;

    @FXML
    private Label soldOut;

    @FXML
    private Spinner<Integer> spinnerChangeNumber;

    private Media media;

    public MediaHomeViewHandler(Media media) {
        this.media = media;
    }

    @FXML
    public void initialize() throws URISyntaxException {
        // Thiết lập dữ liệu từ MediaItem vào các thành phần trong media_home.fxml
        mediaImage.setImage(new Image(getClass().getResource(media.getImageURL()).toURI().toString()));
        mediaTitle.setText(media.getTitle());
        mediaPrice.setText(String.valueOf(media.getPrice()));
        mediaAvail.setText(media.getQuantity() + "");
        if (media.getQuantity() == 0) {
            spinnerChangeNumber.setVisible(false);
            addToCartBtn.setVisible(false);
            soldOut.setVisible(true);
        } else {
            spinnerChangeNumber.setValueFactory(
                    new SpinnerValueFactory.IntegerSpinnerValueFactory(1, media.getQuantity(), 1)
            );
        }
    }

    @FXML
    void handleAddToCart(ActionEvent event) {

    }

}
