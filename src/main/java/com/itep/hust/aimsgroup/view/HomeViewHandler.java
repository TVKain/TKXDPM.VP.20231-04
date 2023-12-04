package com.itep.hust.aimsgroup.view;

import com.itep.hust.aimsgroup.controller.HomeController;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.service.dao.SqliteMediaDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.image.ImageView;
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
    private VBox vboxMedia4;

    private HomeController homeController = new HomeController();

    @FXML
    public void initialize() {
        List<Media> lstMedia = new SqliteMediaDao().getAll();
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
    }
}
