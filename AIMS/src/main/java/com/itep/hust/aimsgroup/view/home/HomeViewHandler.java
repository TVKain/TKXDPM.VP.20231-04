package com.itep.hust.aimsgroup.view.home;
import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.cart.CartViewHandler;
import com.itep.hust.aimsgroup.view.login.LoginViewHandler;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HomeViewHandler {
    /**
     * SOLID analysis
     * Single responsibility principle: lớp này có nhiệm vụ điều khiển màn hình xem danh sách mặt hàng bao gồm chức năng xem danh sách mặt hàng và tìm kiếm (thỏa mãn)
     * Open / CLosed Principle: lớp này không áp dụng kĩ thuật kế thừa
     * Liskov substitution principle: lớp này không áp dụng kĩ thuật kế thừa
     * Interface segregation principle: lớp này không áp dụng kĩ thuật implements
     * Dependency inversion principle: lớp này phụ thuộc vào lớp Media và MediaHomeViewHandler giao tiếp qua interface (thỏa mãn)
     */

    @FXML
    private AnchorPane mainAnchorPane;
    @FXML
    private Label numMediaInCart;
        @FXML
    private VBox vboxMedia1;
    @FXML
    private VBox vboxMedia2;
    @FXML
    private VBox vboxMedia3;
    @FXML
    private ToggleGroup filterPrice;
    @FXML
    private TextField searchBox;
    @FXML
    private VBox vboxMedia4;
    @FXML
    private ComboBox<String> sortByPrice;
    private List<Media> allMedia = new SqliteMediaDao().getAll();
    private ObservableList<Media> listShow = FXCollections.observableArrayList();
    /**
     * Hàm này khởi tạo các giá trị và lấy ra thông tin của media từ database thông qua DAO và hiển thị lên giao diện
     * @author: KhanhND
     */
    @FXML
    public void initialize() {
        if(Cart.getInstance().getMedias().size() > 0) {
            allMedia = updateMedia(allMedia);
            listShow.addAll(allMedia);
        } else {
            listShow.addAll(allMedia);
        }
        showMedia(listShow);
        Cart.getInstance().addChangeListener(e -> {
            updateNumberOfMedia();
        });
        numMediaInCart.setText(Cart.getInstance().getSize() + " media");
        searchBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.isEmpty()) {
                    sortByPrice.getSelectionModel().clearSelection();
                    listShow.clear();
                    listShow.addAll(allMedia);
                    clearMedia();
                    showMedia(listShow);

                }
            }
        });

        sortByPrice.getItems().addAll("Low to high", "High to low");
        sortByPrice.setOnAction(e -> handleSort());
    }

    private void handleSort() {
        Comparator<Media> comparator = Comparator.comparingInt(Media::getValue);
        if(sortByPrice.getValue() == "Low to high") {
            FXCollections.sort(listShow, comparator);
        } else if(sortByPrice.getValue() == "High to low") {
            FXCollections.sort(listShow, comparator.reversed());
        }
        clearMedia();
        showMedia(listShow);
    }

    private ObservableList<Media> getListFilteredByPrice(ObservableList<Media> listMedia, int priceStart, int priceEnd)  {
        ObservableList<Media> listFiltered = FXCollections.observableArrayList();
        if(priceEnd != 0) {
            for(Media media : listMedia) {
                if(media.getPrice() >= priceStart && media.getPrice() <= priceEnd) {
                    listFiltered.add(media);
                }
            }
        } else {
            for(Media media : listMedia) {
                if(media.getPrice() >= priceStart) {
                    listFiltered.add(media);
                }
            }
        }
        return listFiltered;
    }

    private void showMedia(ObservableList<Media> listMedia) {
        clearMedia();
        // Tạo danh sách VBox để lưu trữ các cột
        List<VBox> vboxColumns = new ArrayList<>();
        vboxColumns.add(vboxMedia1);
        vboxColumns.add(vboxMedia2);
        vboxColumns.add(vboxMedia3);
        vboxColumns.add(vboxMedia4); // Thêm vào danh sách
        int i = 0;
        while (i < listMedia.size()) {
            VBox vboxColumn = vboxColumns.get(i%4);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home/media_home.fxml"));
                MediaHomeViewHandler mediaHomeViewHandler = new MediaHomeViewHandler(listMedia.get(i));
                loader.setController(mediaHomeViewHandler);
                loader.load();
                vboxColumn.getChildren().add(loader.getRoot());
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    public List<Media> updateMedia(List<Media> lstMedia) {
        for (Media m : lstMedia) {
            if (Cart.getInstance().getMedias().containsKey(m)) {
                m.setQuantity(m.getQuantity() - Cart.getInstance().getMedias().get(m));
            }
        }
        return lstMedia;
    }
    @FXML
    void search(ActionEvent event) {
        sortByPrice.getSelectionModel().clearSelection();
        String keyWord = searchBox.getText();
        List<Media> listTMP = new ArrayList<>();
        for (Media media: allMedia) {
            if(media.getTitle().contains(keyWord)) {
                listTMP.add(media);
            }
        }
        if(listTMP.size() == 0) {
            Popup.showError("Không tìm thấy sản phẩm !");
        } else {
            listShow.clear();
            clearMedia();
            listShow.addAll(listTMP);
            showMedia(listShow);
        }
    }



    @FXML
    void viewCart(MouseEvent event) {
        Screen.setScreen("/fxml/cart/cart.fxml", new CartViewHandler());
    }

    @FXML
    void login(ActionEvent event) {
        Screen.setScreen("/fxml/login/login.fxml", new LoginViewHandler());
    }

    private void clearMedia() {
        vboxMedia1.getChildren().clear();
        vboxMedia2.getChildren().clear();
        vboxMedia3.getChildren().clear();
        vboxMedia4.getChildren().clear();
    }
    private void updateNumberOfMedia() {
        numMediaInCart.setText(Cart.getInstance().getSize() + " media");
    }

}


