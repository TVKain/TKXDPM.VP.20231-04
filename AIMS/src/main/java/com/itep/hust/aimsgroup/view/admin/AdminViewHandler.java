package com.itep.hust.aimsgroup.view.admin;

import com.itep.hust.aimsgroup.controller.account.AccountController;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.account.Role;
import com.itep.hust.aimsgroup.persistence.dao.AccountDao;
import com.itep.hust.aimsgroup.persistence.dao.RoleDao;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteAccountDao;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteRoleDao;
import com.itep.hust.aimsgroup.subsystem.email.EmailService;
import com.itep.hust.aimsgroup.subsystem.email.javax.JavaxEmailService;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.login.LoginViewHandler;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.util.List;

public class AdminViewHandler {

    @FXML
    private TableView<Account> accountTable;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button logoutButton;

    private final AccountController accountController;
    public AdminViewHandler() {
        AccountDao accountDao = new SqliteAccountDao();
        EmailService emailService = new JavaxEmailService();
        RoleDao roleDao = new SqliteRoleDao();
        this.accountController = new AccountController(accountDao, emailService, roleDao);
    }

    @FXML
    public void initialize() {
        initializeButtons();
        initializeTable();
    }

    public void initializeButtons() {
        logoutButton.setOnMouseClicked(e ->
                Screen.setScreen("/fxml/login/login.fxml", new LoginViewHandler()));

        createAccountButton.setOnMouseClicked(e ->
                Screen.setScreen("/fxml/admin/admin-add.fxml",
                        new AdminCreateViewHandler(accountController)));
    }

    public void initializeTable() {
        TableColumn<Account, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Account, Integer> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Account, String> passwordColumn = new TableColumn<>("Password");
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<Account, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Account, String> rolesColumn = new TableColumn<>("Roles");
        rolesColumn.setCellValueFactory(cellData -> {
           List<String> roleNames =
                   cellData.getValue().getRoles().stream().map(Role::getRoleName).toList();
           return new ReadOnlyObjectWrapper<>(String.join(",", roleNames));
        });

        TableColumn<Account, Void> buttonsColumn = new TableColumn<>();
        buttonsColumn.setSortable(false);
        buttonsColumn.setCellFactory(param -> new AccountButtonCell(accountController));

        TableColumn<Account, Void> statusButtonColumn = new TableColumn<>();
        statusButtonColumn.setSortable(false);
        statusButtonColumn.setCellFactory(param -> new StatusButtonCell(accountController));
        statusButtonColumn.setStyle("-fx-alignment: CENTER;");

        accountTable.setSelectionModel(null);

        accountTable.getColumns().addAll(idColumn, emailColumn, passwordColumn, statusColumn, rolesColumn ,buttonsColumn, statusButtonColumn);

        // Populate the table with data
        List<Account> accountList = accountController.getAccounts();

        ObservableList<Account> data = FXCollections.observableArrayList(accountList);
        accountTable.setItems(data);
    }

    private static class StatusButtonCell extends TableCell<Account, Void> {
        private final AccountController accountController;

        private final Button statusButton;
        public StatusButtonCell(AccountController accountController) {
            this.statusButton = new Button();
            this.accountController = accountController;
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                Account account = getTableView().getItems().get(getIndex());
                statusButton.setCursor(Cursor.HAND);
                switch (account.getStatus()) {
                    case BLOCK -> {
                        String statusButtonText = "Unblock";
                        statusButton.setText(statusButtonText);
                        statusButton.setOnMouseClicked(e -> Popup.showConfirmationDialog("Do you want to unblock this account", () -> {
                            accountController.unblockAccount(account);
                            Screen.setScreen("/fxml/admin/admin.fxml", new AdminViewHandler());
                        }, null));
                    }
                    case ACTIVE -> {
                        String statusButtonText = "Block";
                        statusButton.setText(statusButtonText);
                        statusButton.setOnMouseClicked(e -> Popup.showConfirmationDialog("Do you want to block this account", () -> {
                            accountController.blockAccount(account);
                            Screen.setScreen("/fxml/admin/admin.fxml", new AdminViewHandler());
                        }, null));
                    }
                }
                setGraphic(statusButton);
            }
        }
    }

    private static class AccountButtonCell extends TableCell<Account, Void> {
        private final Button updateButton;
        private final Button deleteButton;


        public AccountButtonCell(AccountController accountController) {
            this.updateButton = new Button("Update");
            this.updateButton.setCursor(Cursor.HAND);
            this.deleteButton = new Button("Delete");
            this.deleteButton.setCursor(Cursor.HAND);


            updateButton.setOnAction(event -> {
                Account account = getTableView().getItems().get(getIndex());
                Screen.setScreen("/fxml/admin/admin-update.fxml",
                        new AdminUpdateViewHandler(account, accountController));
            });

            deleteButton.setOnAction(event -> {
                Account account = getTableView().getItems().get(getIndex());

                Popup.showConfirmationDialog("Do you want to delete this account ?",
                        () -> {
                            accountController.deleteAccount(account);
                            Screen.setScreen("/fxml/admin/admin.fxml", new AdminViewHandler());
                }, null);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                HBox hBox = new HBox();
                hBox.setSpacing(4);

                hBox.getChildren().addAll(updateButton, deleteButton);
                hBox.setAlignment(Pos.CENTER);
                setGraphic(hBox);
            }
        }
    }


}
