package com.itep.hust.aimsgroup.view.cart;

import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.media.Media;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URISyntaxException;
import java.util.Map;

public class CartMediaViewHandler {
    /**
     * SOLID analysis
     * Single responsibility principle: lớp này có nhiệm vụ hiển thị thông tin Media lên màn hình theo format nhất định
     * Open / CLosed Principle: lớp này không áp dụng kĩ thuật kế thừa
     * Liskov substitution principle: lớp này không áp dụng kĩ thuật kế thừa
     * Interface segregation principle: lớp này không áp dụng kĩ thuật implements
     * Dependency inversion principle: lớp này phụ thuộc vào lớp Media giao tiếp qua interface (thỏa mãn)
     */
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
    private Label avail;

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
        avail.setText(String.valueOf(media.getQuantity()));
        image.setImage(new Image(getClass().getResource(media.getImageURL()).toURI().toString()));


        numberOfMedia.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, media.getQuantity() + medias.get(media), 1)
        );
        numberOfMedia.getValueFactory().setValue(medias.get(media));
        if (media.getQuantity() == 0) {
            numberOfMedia.setEditable(false);
        }


        numberOfMedia.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                if (newValue > oldValue) {
                    Cart.getInstance().add(media, 1);
                    price.setText(String.valueOf(media.getPrice() * medias.get(media)));
                    media.setQuantity(media.getQuantity() - 1);
                    avail.setText(String.valueOf(media.getQuantity()));
                } else if (newValue < oldValue) {
                    Cart.getInstance().remove(media);
                    Cart.getInstance().add(media, newValue);
                    price.setText(String.valueOf(media.getPrice() * medias.get(media)));
                    media.setQuantity(media.getQuantity() + 1);
                    avail.setText(String.valueOf(media.getQuantity()));
                }
            }
        });

    }

    @FXML
    void deleteMediaInCart(ActionEvent event) {
        Cart.getInstance().remove(media);
        Pane pane = (Pane) btnDelete.getParent().getParent().getParent();
        VBox vBox = (VBox) pane.getParent();
        vBox.getChildren().remove(pane);
    }

}
