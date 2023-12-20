package com.itep.hust.aimsgroup.view;

import com.itep.hust.aimsgroup.controller.HomeController;
import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Screen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
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
    /**
     * SOLID analysis
     * Single responsibility principle: lớp này có nhiệm vụ điều khiển màn hình xem danh sách mặt hàng bao gồm chức năng xem danh sách mặt hàng và tìm kiếm (thỏa mãn)
     * Open / CLosed Principle: lớp này không áp dụng kĩ thuật kế thừa
     * Liskov substitution principle: lớp này không áp dụng kĩ thuật kế thừa
     * Interface segregation principle: lớp này không áp dụng kĩ thuật implements
     * Dependency inversion principle: lớp này phụ thuộc vào lớp Media và MediaHomeViewHandler giao tiếp qua interface (thỏa mãn)
     */


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
    private int currentList = 0;
    /**
     * Hàm này khởi tạo các giá trị và lấy ra thông tin của media từ database thông qua DAO và hiển thị lên giao diện
     * @author: KhanhND
     */
    @FXML
    public void initialize() {
        Alert a = new Alert(Alert.AlertType.INFORMATION);

        // Sửa List thành ObservableList để lắng nghe thay đổi
        ObservableList<Media> lstMedia = FXCollections.observableArrayList();
        lstMedia.addAll(new SqliteMediaDao().getAll());
        if(Cart.getInstance().getMedias().size() > 0) {
            updateMedia(lstMedia);
        }
        // List filtered lưu danh sách kết quả khớp với tìm kiếm
        ObservableList<Media> filtered = FXCollections.observableArrayList();
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

        // Nếu searchbox trống và danh sách đang được hiển thị là Filtered thì chuyển về danh sách ban đầu
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            if ((newValue.isEmpty()) && (currentList==1)) {
                refresh(lstMedia, vboxColumns);
                currentList=0;
            }
        });
        splitMenuBtnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // kiểm tra xem searchbox có trống không
                if (!searchBox.getText().isEmpty()){
                    // xóa toàn bộ list filtered
                    filtered.clear();
                    int i = 0;
                    while (i< lstMedia.size()){
                        // tìm kiếm dựa theo tên sản phẩm
                        if ( lstMedia.get(i).getTitle().contains(searchBox.getCharacters())){
                            // thêm sản phẩm trùng khớp vào list filtered
                            filtered.add(lstMedia.get(i));
                        }
                        i++;
                    }
                   // nếu danh sách filtered trống thì thông báo tới người dùng
                    if (filtered.isEmpty()){
                        a.setContentText("No results were found");
                        a.show();
                    }
                    else {
                        // nếu danh sách filtered không trống thay danh sách toàn bộ sản phẩm bằng danh sách đã được lọc
                        refresh(filtered, vboxColumns);
                        currentList=1;
                    }
                }
            }
        });
    }

    private void updateNumberOfMedia() {
        numMediaInCart.setText(Cart.getInstance().getSize() + " media");
    }

    @FXML
    public void refresh(ObservableList<Media> lstMedia, List<VBox> vboxColumns  ){
        int i = 0;
        //xóa toàn bộ item khỏi các cột
        vboxColumns.get(0).getChildren().clear();
        vboxColumns.get(1).getChildren().clear();
        vboxColumns.get(2).getChildren().clear();
        vboxColumns.get(3).getChildren().clear();
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

    public void updateMedia(List<Media> lstMedia) {
        for (Media m : lstMedia) {
            if (Cart.getInstance().getMedias().containsKey(m)) {
                m.setQuantity(m.getQuantity() - Cart.getInstance().getMedias().get(m));
            }
        }
    }

    @FXML
    void viewCart(MouseEvent event) {
        Screen.setScreen("/fxml/cart/cart.fxml", new CartViewHandler());
    }
}
