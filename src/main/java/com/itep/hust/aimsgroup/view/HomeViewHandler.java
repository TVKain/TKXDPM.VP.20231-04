package com.itep.hust.aimsgroup.view;

import com.itep.hust.aimsgroup.controller.HomeController;
import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.service.dao.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Screen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeViewHandler {

    @FXML
    private ImageView aimsImage;

    @FXML
    private ImageView cartImage;

    @FXML
    private HBox hboxMedia;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private Label numMediaInCart;

    @FXML
    private SplitMenuButton splitMenuBtnSearch;

    @FXML
    private VBox vboxMedia1;

    @FXML
    private VBox vboxMedia2;

    @FXML
    private VBox vboxMedia3;

    @FXML
    private TextField searchBox;

    @FXML
    private VBox vboxMedia4;

    private HomeController homeController = new HomeController();

    public HomeViewHandler() {
    }

    /**
     * Hàm này khởi tạo các giá trị và lấy ra thông tin của media từ database thông qua DAO và hiển thị lên giao diện
     * @author: KhanhND
     */
    @FXML
    public void initialize() {
        // Sửa List thành ObservableList để lắng nghe thay đổi
        ObservableList<Media> lstMedia = FXCollections.observableArrayList();
        lstMedia.addAll(new SqliteMediaDao().getAll());

        // Tạo danh sách VBox để lưu trữ các cột
        List<VBox> vboxColumns = new ArrayList<>();
        vboxColumns.add(vboxMedia1);
        vboxColumns.add(vboxMedia2);
        vboxColumns.add(vboxMedia3);
        vboxColumns.add(vboxMedia4); // Thêm vào danh sách
        int i = 0;
        while (i < lstMedia.size()) {
            VBox vboxColumn = vboxColumns.get(i%4);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home/media_home.fxml"));
                MediaHomeViewHandler mediaHomeViewHandler = new MediaHomeViewHandler(lstMedia.get(i));
                loader.setController(mediaHomeViewHandler);
                loader.load();
                vboxColumn.getChildren().add(loader.getRoot());
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }

        Cart.getInstance().addChangeListener(e -> {
            updateNumberOfMedia();
        });
        numMediaInCart.setText(Cart.getInstance().getSize() + " media");
        splitMenuBtnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (searchBox.getText() != null){
                    int i = 0;
                    while (i< lstMedia.size()){
                        if ( !lstMedia.get(i).getTitle().contains(searchBox.getCharacters())){
                            System.out.println(lstMedia.get(i).getTitle());
                            lstMedia.remove(lstMedia.get(i));
                        }
                        i++;
                    }
                }
            }
        });
    }

    private void updateNumberOfMedia() {
        numMediaInCart.setText(Cart.getInstance().getSize() + " media");
    }

    @FXML
    public void handleSearch(){

    }

    @FXML
    public void refresh(ObservableList<Media> lstMedia, List<VBox> vboxColumns  ){
        int i = 0;
        while (i < lstMedia.size()) {
            try {
                VBox vboxColumn = vboxColumns.get(i%4);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home/media_home.fxml"));
                MediaHomeViewHandler mediaHomeViewHandler = new MediaHomeViewHandler(lstMedia.get(i));
                loader.setController(mediaHomeViewHandler);
                loader.load();
                vboxColumn.getChildren().add(loader.getRoot());
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

    @FXML
    void viewCart(MouseEvent event) {
        Screen.setScreen("/fxml/cart/cart.fxml", new CartViewHandler());
    }
}
