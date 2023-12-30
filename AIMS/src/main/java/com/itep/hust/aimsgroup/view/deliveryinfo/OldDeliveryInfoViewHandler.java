package com.itep.hust.aimsgroup.view.deliveryinfo;

import com.itep.hust.aimsgroup.controller.placeorder.PlaceOrderController;
import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.deliveryinfo.type.DeliveryType;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.CartViewHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class OldDeliveryInfoViewHandler {
    private final PlaceOrderController placeOrderController = new PlaceOrderController();
    @FXML
    private GridPane mediaGrid;
    @FXML
    private TextField addressTextField;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private Button confirmDeliveryButton;

    @FXML
    private GridPane formGrid;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private ComboBox<DeliveryType> deliveryTypeComboBox;

    @FXML
    private TextField shippingInstructionTextField;

    @FXML
    private Label rushInstructionLabel;

    @FXML
    private TextField rushInstructionTextField;

    @FXML
    private Label rushTimeLabel;
    @FXML
    private TextField rushTimeTextField;


    @FXML
    public void initialize() {
        initializeDeliveryTypeComboBox();
        hideRushOrderFields();
        initializeMediaGrid();
        initializeBackButton();
        initializeCityComboBox();
        initializeConfirmDeliveryButton();
    }

    public void initializeCityComboBox() {
        // TODO Replace with call to database
        cityComboBox.getItems().addAll("Ha Noi", "Bac Ninh", "TP. HCM", "Da Nang");
        cityComboBox.getSelectionModel().selectFirst();
    }

    public void initializeConfirmDeliveryButton() {
        confirmDeliveryButton.setOnMouseClicked(e -> {
            Map<String, String> deliveryInfoData = this.getFormData();
            DeliveryType deliveryType = deliveryTypeComboBox.getValue();

            placeOrderController.setDeliveryInfoValidator(deliveryType);
            if (!placeOrderController.validateDeliveryInfo(deliveryInfoData)) {
                return;
            }

            placeOrderController.setDeliveryInfo(deliveryType, deliveryInfoData);

            // Go to next screen
            System.out.println("Go to next screen");
        });
    }

    private void initializeDeliveryTypeComboBox() {
        deliveryTypeComboBox.getItems().addAll(DeliveryType.values());
        deliveryTypeComboBox.getSelectionModel().select(DeliveryType.NORMAL);

        deliveryTypeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case NORMAL -> hideRushOrderFields();
                case RUSH -> showRushOrderFields();
            }
        });
    }

    private Map<String, String> getFormData() {
        String name = nameTextField.getText();
        String phone = phoneTextField.getText();
        String instruction = shippingInstructionTextField.getText();
        String city = cityComboBox.getValue();
        String address = addressTextField.getText();
        String rushInstruction = rushInstructionTextField.getText();
        String rushTime = rushTimeTextField.getText();

        Map<String, String> formData = new HashMap<>();

        formData.put("name", name);
        formData.put("phone", phone);
        formData.put("instruction", instruction);
        formData.put("city", city);
        formData.put("address", address);
        formData.put("rushInstruction", rushInstruction);
        formData.put("rushTime", rushTime);

        return formData;
    }

    public void initializeBackButton() {
        backButton.setOnMouseClicked(e -> Screen.setScreen("/fxml/cart/cart.fxml", new CartViewHandler()));
    }

    public void initializeMediaGrid() {
        Set<Media> mediaSet = Cart.getInstance().getMedias().keySet();

        int rowCount = 1;
        for (Media media : mediaSet) {
            Label title = new Label(media.getTitle());
            title.setAlignment(Pos.CENTER);

            Label rushDelivery = new Label(media.isRushDelivery() ? "Yes" : "No");
            rushDelivery.setAlignment(Pos.CENTER);


            mediaGrid.addRow(rowCount, title, rushDelivery);
            ++rowCount;
        }
    }



    public void showRushOrderFields() {
        formGrid.addRow(6, rushInstructionLabel, rushInstructionTextField);
        formGrid.addRow(7, rushTimeLabel, rushTimeTextField);
    }

    public void hideRushOrderFields() {
        formGrid.getChildren().removeAll(rushInstructionLabel, rushInstructionTextField,
                rushTimeLabel, rushTimeTextField);
    }
}
