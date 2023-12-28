package com.itep.hust.aimsgroup.view;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Screen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ManagerViewHandler implements Initializable {
    @FXML
    private TableColumn<Media, String> category;

    @FXML
    private TableColumn<Media, Integer> id;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private TableColumn<Media, Integer> price;

    @FXML
    private TableColumn<Media, Integer> quantity;

    @FXML
    private TableColumn<?, ?> rushOrder;

    @FXML
    private TableColumn<Media, String> title;

    @FXML
    private TableColumn<Media, Integer> value;

    @FXML
    private TableColumn<Media, Double> weight;
    @FXML
    private TableView<Media> tableMedia;

    private ObservableList<Media> listMedia = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.listMedia.addAll(new SqliteMediaDao().getAll());
        id.setCellValueFactory(new PropertyValueFactory<Media, Integer>("id"));
        price.setCellValueFactory(new PropertyValueFactory<Media, Integer>("price"));
        title.setCellValueFactory(new PropertyValueFactory<Media, String>("title"));
        category.setCellValueFactory(new PropertyValueFactory<Media, String>("category"));
        value.setCellValueFactory(new PropertyValueFactory<Media, Integer>("value"));
        weight.setCellValueFactory(new PropertyValueFactory<Media, Double>("weight"));
        quantity.setCellValueFactory(new PropertyValueFactory<Media, Integer>("quantity"));
        tableMedia.setItems(listMedia);
    }

    @FXML
    void logout(ActionEvent event) {
        Screen.setScreen("/fxml/login-admin/login-admin.fxml", new AdminLoginViewHandler());
    }
}
