package com.itep.hust.aimsgroup.view;

import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.util.Screen;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URISyntaxException;
import java.util.Map;

public class CartMediaViewHandler {

    @FXML
    private Button btnDelete;

    @FXML
    private Label currency;

    @FXML
    private VBox description;

    @FXML
    private HBox hboxMedia;

    @FXML
    private ImageView image;

    @FXML
    private VBox imageLogoVbox;

    @FXML
    private Label price;

    @FXML
    private Label title;

    private Media media;

    public CartMediaViewHandler(Media media) {
        this.media = media;
    }

    @FXML
    public void initialize() throws URISyntaxException {
        Map<Media, Integer> medias = Cart.getInstance().getMedias();
        title.setText(media.getTitle());
        price.setText(String.valueOf(media.getPrice() * medias.get(media)));
        image.setImage(new Image(getClass().getResource(media.getImageURL()).toURI().toString()));
    }
    @FXML
    void deleteMediaInCart(ActionEvent event) {
        title.setText(this.media.getTitle());
    }

}
