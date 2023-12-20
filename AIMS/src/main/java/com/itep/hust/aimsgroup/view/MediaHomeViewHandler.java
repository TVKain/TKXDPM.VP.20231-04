package com.itep.hust.aimsgroup.view;

import com.itep.hust.aimsgroup.model.cart.Cart;
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
    /**
     * SOLID analysis
     * Single responsibility principle: lớp này có nhiệm vụ hiển thị thông tin Media lên màn hình theo format nhất định
     * Open / CLosed Principle: lớp này không áp dụng kĩ thuật kế thừa
     * Liskov substitution principle: lớp này không áp dụng kĩ thuật kế thừa
     * Interface segregation principle: lớp này không áp dụng kĩ thuật implements
     * Dependency inversion principle: lớp này phụ thuộc vào lớp Media và Cart giao tiếp qua interface (thỏa mãn)
     */

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

    /**
     * Vi phạm stamp coupling vì khi truyền media để hiển thị được lên giao diện home, ta chỉ cần truyền 4 trường
     * title, urlImage, price, và quantity nhưng ta vẫn truyền cả Media. nhưng truyền cả Media sẽ giúp chúng ta thuận tiện
     * khi truyền media qua các màn hình, và tiện khi xem chi tiết 1 sản phẩm khi người dùng muốn xem chi tiết
     * @param media
     */

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
        Cart.getInstance().add(media, spinnerChangeNumber.getValue());
        media.setQuantity(media.getQuantity() - spinnerChangeNumber.getValue());
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

}
