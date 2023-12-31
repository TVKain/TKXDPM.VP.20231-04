package com.itep.hust.aimsgroup.view;

import com.itep.hust.aimsgroup.controller.HomeController;
import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Screen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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
    private Button splitMenuBtnSearch;

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

    @FXML
    private ChoiceBox<String> attribute;

    @FXML
    private MenuButton sort;

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

        MenuItem menuItem0 = new MenuItem("Default");
        MenuItem menuItem1 = new MenuItem("Price lowest first");
        MenuItem menuItem2 = new MenuItem("Price highest first");
        MenuItem menuItem3 = new MenuItem("A to Z");
        MenuItem menuItem4 = new MenuItem("Z to A");

        sort.getItems().add(menuItem0);
        sort.getItems().add(menuItem1);
        sort.getItems().add(menuItem2);
        sort.getItems().add(menuItem3);
        sort.getItems().add(menuItem4);
        sort.setText("Sorting");

        attribute.getItems().add("Title");
        attribute.getItems().add("Price");
        attribute.getItems().add("Amount");
        attribute.setValue("Title");

        // Nếu searchbox trống và danh sách đang được hiển thị là Filtered thì chuyển về danh sách ban đầu
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            if ((newValue.isEmpty()) && (currentList==1)) {
                refresh(lstMedia, vboxColumns);
                currentList=0;
            }
        });
        menuItem0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (currentList == 1){
                    filtered.sort(Comparator.comparing(Media::getId));
                    refresh(filtered, vboxColumns);
                    sort.setText("Default");

                }
                else {
                    lstMedia.sort(Comparator.comparing(Media::getId));
                    refresh(lstMedia, vboxColumns);
                    sort.setText("Default");
                }
            }
        });
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (currentList == 1){
                    filtered.sort(Comparator.comparing(Media::getPrice));
                    refresh(filtered, vboxColumns);
                    sort.setText("Price lowest first");

                }
                else {
                    lstMedia.sort(Comparator.comparing(Media::getPrice));
                    refresh(lstMedia, vboxColumns);
                    sort.setText("Price lowest first");
                }
            }
        });

        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (currentList == 1){
                    filtered.sort(Comparator.comparing(Media::getPrice).reversed());
                    refresh(filtered, vboxColumns);
                    sort.setText("Price highest first");

                }
                else {
                    lstMedia.sort(Comparator.comparing(Media::getPrice).reversed());
                    refresh(lstMedia, vboxColumns);
                    sort.setText("Price highest first");
                }
            }
        });

        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (currentList == 1){
                    filtered.sort(Comparator.comparing(Media::getTitle));
                    refresh(filtered, vboxColumns);
                    sort.setText("A to Z");

                }
                else {
                    lstMedia.sort(Comparator.comparing(Media::getTitle));
                    refresh(lstMedia, vboxColumns);
                    sort.setText("A to Z");
                }
            }
        });

        menuItem4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (currentList == 1){
                    filtered.sort(Comparator.comparing(Media::getTitle).reversed());
                    refresh(filtered, vboxColumns);
                    sort.setText("Z to A");

                }
                else {
                    lstMedia.sort(Comparator.comparing(Media::getTitle).reversed());
                    refresh(lstMedia, vboxColumns);
                    sort.setText("Z to A");
                }
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
                        if  ( (attribute.getValue().equals("Title")) &&  ( lstMedia.get(i).getTitle().contains(searchBox.getCharacters())) ){
                            // thêm sản phẩm trùng khớp vào list filtered
                            filtered.add(lstMedia.get(i));
                        }
                        if  ( (attribute.getValue().equals("Price")) &&  ( compare( lstMedia.get(i).getPrice() , searchBox.getCharacters().toString()) ) ){
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

    boolean compare(int number, String input){
        int number2, number3;
        String input2 = input.replaceAll("\\s", "");

        if (input2.contains("-")){
            String[] parts = input2.split("-");
            number2 = tryParse(parts[0]);
            number3 = tryParse(parts[1]);
            if ((number2 <= number) && (number <= number3)) return true;
        }

        if (isNumeric(input2)){
            if (number == tryParse(input2))
                return true;
        }

         if ( (input2.substring(0,2).matches("<=")) || (input2.substring(0,2).matches("=<")) ) {
             input2 = input2.replace("<","");
             input2 = input2.replace("=","");
             number2 = tryParse(input2);
             if (number <= number2) return true;
         }

        if ( (input2.substring(0,2).matches(">=")) || (input2.substring(0,2).matches("=>")) ) {
            input2 = input2.replace(">","");
            input2 = input2.replace("=","");
            number2 = tryParse(input2);
            if (number >= number2) return true;
        }

        switch (input2.charAt(0)){
            case '<':
                input2 = input2.replace("<","");
                number2 = tryParse(input2);
                if (number < number2) return true;
                break;
            case '>':
                input2 = input2.replace(">","");
                number2 = tryParse(input2);
                if (number > number2) return true;
                break;
        }
        return false;
    }

    boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }

    Integer tryParse(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 2147000000;
        }
    }
}
