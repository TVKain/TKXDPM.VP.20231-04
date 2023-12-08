package com.itep.hust.aimsgroup.view;

import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.util.Screen;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
    @FXML
    private Spinner<Integer> numberOfMedia;

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

        numberOfMedia.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, media.getQuantity(), 1)
        );
        numberOfMedia.getValueFactory().setValue(medias.get(media));

        numberOfMedia.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                if (newValue > oldValue) {
                    Cart.getInstance().add(media, 1);
                    price.setText(String.valueOf(media.getPrice() * medias.get(media)));
                } else if (newValue < oldValue) {
                    Cart.getInstance().remove(media);
                    Cart.getInstance().add(media, newValue);
                    price.setText(String.valueOf(media.getPrice() * medias.get(media)));
                }

            }
        });

    }
    @FXML
    void deleteMediaInCart(ActionEvent event) {
        title.setText(this.media.getTitle());
    }

}
