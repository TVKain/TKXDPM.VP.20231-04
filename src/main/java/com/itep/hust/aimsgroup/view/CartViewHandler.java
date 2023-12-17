package com.itep.hust.aimsgroup.view;

import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.util.Screen;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Map;

public class CartViewHandler {

    @FXML
    private Label VAT;

    @FXML
    private ImageView aimsImage;

    @FXML
    private Label amount;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Label labelSubtotal;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label pageTitle;

    @FXML
    private Label subtotal;

    @FXML
    private VBox vboxCart;

    public CartViewHandler() {
    }

    @FXML
    public void initialize() {
        vboxCart.getChildren().clear();
        Map<Media, Integer> medias = Cart.getInstance().getMedias();
        medias.forEach((media, quantity) -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cart/media_cart.fxml"));
            CartMediaViewHandler cartMediaViewHandler = new CartMediaViewHandler(media);
            loader.setController(cartMediaViewHandler);
            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            vboxCart.getChildren().add(loader.getRoot());
        }) ;

        int subTotal = Cart.getInstance().getTotalPrice();
        int vat = (int) (subTotal * 0.1);

        subtotal.setText(String.valueOf(subTotal));
        VAT.setText(String.valueOf(vat));
        amount.setText(String.valueOf(subTotal + vat));

        Cart.getInstance().addChangeListener(e -> {
            updateLabel();
        });
    }
    @FXML
    void PlaceOrder(ActionEvent event) {
        Screen.setScreen("/fxml/delivery-info/delivery-info.fxml", new DeliveryInfoViewHandler());
    }
    public void updateLabel() {
        int subTotal = Cart.getInstance().getTotalPrice();
        int vat = (int) (subTotal * 0.1);

        subtotal.setText(String.valueOf(subTotal));
        VAT.setText(String.valueOf(vat));
        amount.setText(String.valueOf(subTotal + vat));
    }
    @FXML
    void backToPrevPage(ActionEvent event) {
        Screen.setScreen("/fxml/home/home.fxml", new HomeViewHandler());
    }

}
