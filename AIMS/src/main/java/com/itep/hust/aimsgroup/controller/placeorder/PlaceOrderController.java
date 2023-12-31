package com.itep.hust.aimsgroup.controller.placeorder;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.invoice.Invoice;
import com.itep.hust.aimsgroup.subsystem.Banking;
import com.itep.hust.aimsgroup.subsystem.vnpay.VNPaySubsystem;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.payment.PaymentViewHandler;

public class PlaceOrderController {
    public void redirectToPayment(Invoice invoice) {
        Banking banking = new VNPaySubsystem();

        banking.processTransaction(invoice.getTotal(), () -> {
            Screen.setScreen("/fxml/payment/payment-success.fxml", new PaymentViewHandler());
        }, () -> {
            Screen.setScreen("/fxml/payment/payment-failure.fxml", new PaymentViewHandler());
        });
    }
}
