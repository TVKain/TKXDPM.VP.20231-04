package com.itep.hust.aimsgroup.view.manager;

import com.itep.hust.aimsgroup.controller.manager.ManagerController;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;

import com.itep.hust.aimsgroup.view.login.LoginViewHandler;
import com.itep.hust.aimsgroup.view.manager.add.AddGenenalInfomation;
import com.itep.hust.aimsgroup.view.manager.edit.EditGeneralViewHandler;
import com.itep.hust.aimsgroup.view.manager.view.DetailGeneralViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
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
    private TableColumn<Media, Integer> rushDelivery;

    @FXML
    private TableColumn<Media, String> title;

    @FXML
    private TableColumn<Media, Integer> value;

    @FXML
    private TableColumn<Media, Double> weight;
    @FXML
    private TableView<Media> tableMedia;
    @FXML
    private AnchorPane mainContent;
    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button viewButton;

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
        rushDelivery.setCellValueFactory(new PropertyValueFactory<Media, Integer>("rushDelivery"));
        tableMedia.setItems(listMedia);
        tableMedia.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableMedia.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Media>() {
            @Override
            public void onChanged(Change<? extends Media> change) {
                int selectedRowCount = tableMedia.getSelectionModel().getSelectedItems().size();
                if (selectedRowCount == 1) {
                    viewButton.setVisible(true);
                    editButton.setVisible(true);
                    deleteButton.setVisible(true);
                } else if (selectedRowCount > 1) {
                    viewButton.setVisible(false);
                    editButton.setVisible(false);
                } else if ( selectedRowCount > 10) {
                    viewButton.setVisible(false);
                    editButton.setVisible(false);
                    deleteButton.setVisible(false);
                    Popup.showError("Không thể  xóa cùng lúc hơn 10 sản phẩm !");
                }
            }
        });

    }

    @FXML
    void logout(ActionEvent event) {
        Screen.setScreen("/fxml/login/login.fxml", new LoginViewHandler());
    }
    @FXML
    void addNewMedia(ActionEvent event) throws IOException {
        Screen.setScreen("/fxml/manager/add/add_media.fxml", new AddGenenalInfomation());
    }

    @FXML
    void deleteMedia(ActionEvent event) {
        ObservableList<Media> listDelete =  tableMedia.getSelectionModel().getSelectedItems();
        ManagerController managerController = new ManagerController(new SqliteMediaDao());
        if(listDelete.size() <= 10) {
            for(Media media: listDelete) {
                managerController.deleteMedia(media);
            }
            listMedia.removeAll(listDelete);
        } else {
            Popup.showError("Không thể xóa hơn 10 media cùng lúc!");
        }
    }

    @FXML
    void editMedia(ActionEvent event) {
        Media media = tableMedia.getSelectionModel().getSelectedItem();
        Screen.setScreen("/fxml/manager/edit/edit_media.fxml", new EditGeneralViewHandler(media));
    }

    @FXML
    void viewDetailMedia(ActionEvent event) {
        Media media = tableMedia.getSelectionModel().getSelectedItem();
        Screen.setScreen("/fxml/manager/view/view_detail_media.fxml", new DetailGeneralViewHandler(media));
    }
}
