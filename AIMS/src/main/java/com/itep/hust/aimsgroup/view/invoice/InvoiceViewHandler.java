package com.itep.hust.aimsgroup.view.invoice;

import com.itep.hust.aimsgroup.controller.placeorder.PlaceOrderController;
import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.invoice.Invoice;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.service.email.javax.JavaxEmailService;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.deliveryinfo.DeliveryInfoViewHandler;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Map;

public class InvoiceViewHandler {
    private final Invoice invoice;

    private final PlaceOrderController placeOrderController = new PlaceOrderController();

    public InvoiceViewHandler(Invoice invoice) {
        this.invoice = invoice;
    }

    @FXML
    TableView<Map.Entry<Media, Integer>> mediaTable;

    @FXML
    Button backButton;

    @FXML
    private Label mediaTotalLabel;

    @FXML
    private Label mediaSubtotalLabel;

    @FXML
    private Button paymentButton;

    @FXML
    private Label shippingFeeLabel;

    @FXML
    private Label totalLabel;

    @FXML
    private Label vatLabel;

    @FXML
    public void initialize() {
        initializeBackButton();
        initializeMediaTable();
        initializeLabels();
        initializePaymentButton();
    }

    private void initializePaymentButton() {
        paymentButton.setOnMouseClicked(e -> {
            placeOrderController.redirectToPayment(invoice, new JavaxEmailService());
        });
    }

    private void initializeLabels() {
        vatLabel.setText(String.format("%.2f %%", invoice.getVat()));
        mediaTotalLabel.setText(String.format("%.2f VND", invoice.getMediaTotal()));
        mediaSubtotalLabel.setText(String.format("%.2f VND", invoice.getMediaSubtotal()));
        shippingFeeLabel.setText(String.format("%.2f VND", invoice.getShippingFee()));
        totalLabel.setText(String.format("%.2f VND", invoice.getTotal()));
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

    public void initializeBackButton() {
        backButton.setOnMouseClicked(e -> Screen.setScreen("/fxml/delivery-info/delivery-info.fxml", new DeliveryInfoViewHandler()));
    }
}
