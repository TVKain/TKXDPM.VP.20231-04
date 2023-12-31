package com.itep.hust.aimsgroup.controller.placeorder;

import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.invoice.Invoice;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.service.email.JavaMailUtil;
import com.itep.hust.aimsgroup.service.html.HTMLParse;
import com.itep.hust.aimsgroup.subsystem.Banking;
import com.itep.hust.aimsgroup.subsystem.vnpay.VNPaySubsystem;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.payment.PaymentViewHandler;

import java.text.DecimalFormat;
import java.util.Map;

public class PlaceOrderController {
    public void redirectToPayment(Invoice invoice) {
        Banking banking = new VNPaySubsystem();

        banking.processTransaction(invoice.getTotal(), () -> {
            Screen.setScreen("/fxml/payment/payment-success.fxml", new PaymentViewHandler());
            try {
                JavaMailUtil.sendMail(invoice.getDeliveryInfo().getEmail(), HTMLParse.InvoiceParse(invoice));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, () -> {
            Screen.setScreen("/fxml/payment/payment-failure.fxml", new PaymentViewHandler());
        });
    }
}
