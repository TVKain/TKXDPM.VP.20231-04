package com.itep.hust.aimsgroup.view.manager;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.media.book.Book;
import com.itep.hust.aimsgroup.model.media.cd.CD;
import com.itep.hust.aimsgroup.model.media.dvd.DVD;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteMediaDao;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;

import com.itep.hust.aimsgroup.view.login.LoginViewHandler;
import com.itep.hust.aimsgroup.view.manager.add.AddGerenalInfomation;
import com.itep.hust.aimsgroup.view.manager.edit.EditBookViewHandler;
import com.itep.hust.aimsgroup.view.manager.edit.EditDVDViewHandler;
import com.itep.hust.aimsgroup.view.manager.view.DetailBookViewHandler;
import com.itep.hust.aimsgroup.view.manager.view.DetailDVDViewHandler;
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
        Screen.setScreen("/fxml/manager/add/add_media.fxml", new AddGerenalInfomation());
    }

    @FXML
    void deleteMedia(ActionEvent event) {
        ObservableList<Media> listDelete =  tableMedia.getSelectionModel().getSelectedItems();
        if(listDelete.size() <= 10) {
            SqliteMediaDao sqliteMediaDao = new SqliteMediaDao();
            for(Media media: listDelete) {
                sqliteMediaDao.delete(media);
            }
            listMedia.removeAll(listDelete);
        } else {
            Popup.showError("Không thể xóa hơn 10 media cùng lúc!");
        }
    }

    @FXML
    void editMedia(ActionEvent event) {
        Media media = tableMedia.getSelectionModel().getSelectedItem();
        if(media != null) {
            if(media instanceof Book) {
                Screen.setScreen("/fxml/manager/edit/edit_book.fxml", new EditBookViewHandler((Book)media));
            } else if (media instanceof DVD) {
                Screen.setScreen("/fxml/manager/edit/edit_dvd.fxml", new EditDVDViewHandler((DVD) media));
            } else if (media instanceof CD) {
            }
        }
    }

    @FXML
    void viewDetailMedia(ActionEvent event) {
        Media media = tableMedia.getSelectionModel().getSelectedItem();
        if(media != null) {
            if(media instanceof Book) {
                Screen.setScreen("/fxml/manager/view/view_detail_book.fxml", new DetailBookViewHandler((Book)media));
            } else if (media instanceof DVD) {
                Screen.setScreen("/fxml/manager/view/view_detail_dvd.fxml", new DetailDVDViewHandler((DVD)media));
            } else if (media instanceof CD) {
            }
        }
    }
}
