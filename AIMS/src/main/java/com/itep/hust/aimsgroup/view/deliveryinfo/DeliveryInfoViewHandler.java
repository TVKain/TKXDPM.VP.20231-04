package com.itep.hust.aimsgroup.view.deliveryinfo;

import com.itep.hust.aimsgroup.controller.placeorder.invoice.InvoiceController;
import com.itep.hust.aimsgroup.model.account.Account;
import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.deliveryinfo.RushDeliveryInfo;
import com.itep.hust.aimsgroup.model.deliveryinfo.type.DeliveryType;
import com.itep.hust.aimsgroup.model.invoice.Invoice;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.util.ComponentLoader;
import com.itep.hust.aimsgroup.util.Popup;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.CartViewHandler;
import com.itep.hust.aimsgroup.view.deliveryinfo.form.DeliveryInfoFormViewHandler;
import com.itep.hust.aimsgroup.view.deliveryinfo.form.NormalDeliveryInfoFormViewHandler;
import com.itep.hust.aimsgroup.view.deliveryinfo.form.RushDeliveryInfoFormViewHandler;
import com.itep.hust.aimsgroup.view.invoice.InvoiceViewHandler;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.util.Map;

public class DeliveryInfoViewHandler {
    @FXML
    ComboBox<DeliveryType> deliveryTypeComboBox;

    @FXML
    Pane formContainer;

    @FXML
    Button backButton;

    @FXML
    Button confirmDeliveryButton;

    @FXML
    TableView<Map.Entry<Media, Integer>> mediaTable;
    private DeliveryInfoFormViewHandler deliveryInfoFormViewHandler = new NormalDeliveryInfoFormViewHandler();

    private InvoiceController invoiceController = new InvoiceController();
    @FXML
    public void initialize() {
        initializeFormContainer();
        initializeDeliveryTypeComboBox();
        initializeBackButton();
        initializeConfirmDeliveryButton();
        initializeMediaTable();
    }

    private void initializeFormContainer() {
        formContainer.getChildren().add(ComponentLoader.getComponent("/fxml/delivery-info/delivery-info-form.fxml", this.deliveryInfoFormViewHandler));
    }

    private void initializeMediaTable() {
        TableColumn<Map.Entry<Media, Integer>, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getKey().getId()));
        idColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Map.Entry<Media, Integer>, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getKey().getTitle()));

        TableColumn<Map.Entry<Media, Integer>, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getKey().getCategory()));

        TableColumn<Map.Entry<Media, Integer>, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getKey().getPrice()));

        TableColumn<Map.Entry<Media, Integer>, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getValue()));

        TableColumn<Map.Entry<Media, Integer>, String> rushSupportColumn = new TableColumn<>("Support rush order");
        rushSupportColumn.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(cell.getValue().getKey().getRushDelivery() == 1 ? "Yes" : "No"));
        rushSupportColumn.setStyle("-fx-alignment: CENTER;");

        mediaTable.setSelectionModel(null);
        mediaTable.getColumns().addAll(idColumn, titleColumn, categoryColumn, priceColumn, quantityColumn, rushSupportColumn);

        mediaTable.setItems(FXCollections.observableList(Cart.getInstance().getMedias().entrySet().stream().toList()));
    }

    private void initializeConfirmDeliveryButton() {
        confirmDeliveryButton.setOnMouseClicked(e -> {
            Pair<String, Boolean> validation = this.deliveryInfoFormViewHandler.validateValue();

            if (!validation.getValue()) {
                Popup.showError(validation.getKey());

                return;
            }

            DeliveryInfo deliveryInfo = this.deliveryInfoFormViewHandler.getDeliveryInfoFactory()
                    .getDeliveryInfo(this.deliveryInfoFormViewHandler.getFormValue());

            double shippingFee = this.deliveryInfoFormViewHandler.getShippingFeeCalculator().calculateShip(deliveryInfo);

            System.out.println(shippingFee);
            System.out.println(deliveryInfo);

            Invoice invoice = invoiceController.createInvoice(deliveryInfo, shippingFee);

            Screen.setScreen("/fxml/invoice/invoice.fxml", new InvoiceViewHandler(invoice));
        });
    }


    private void initializeDeliveryTypeComboBox() {
        deliveryTypeComboBox.getItems().addAll(DeliveryType.values());
        deliveryTypeComboBox.getSelectionModel().select(DeliveryType.NORMAL);

        deliveryTypeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCurrentForm(newValue);
        });
    }

    private void setCurrentForm(DeliveryType deliveryType) {
        formContainer.getChildren().clear();

        Parent form = switch (deliveryType) {
            case NORMAL -> {
                this.deliveryInfoFormViewHandler = new NormalDeliveryInfoFormViewHandler();
                yield ComponentLoader.getComponent("/fxml/delivery-info/delivery-info-form.fxml",
                        this.deliveryInfoFormViewHandler);
            }
            case RUSH -> {
                this.deliveryInfoFormViewHandler = new RushDeliveryInfoFormViewHandler();
                yield ComponentLoader.getComponent("/fxml/delivery-info/rush-delivery-info-form.fxml",
                        this.deliveryInfoFormViewHandler);
            }
        };

        formContainer.getChildren().add(form);
    }

    public void initializeBackButton() {
        backButton.setOnMouseClicked(e -> Screen.setScreen("/fxml/cart/cart.fxml", new CartViewHandler()));
    }


}
