package com.itep.hust.aimsgroup.controller.placeorder;

import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.invoice.Invoice;
import com.itep.hust.aimsgroup.persistence.dao.InvoiceDao;
import com.itep.hust.aimsgroup.subsystem.email.EmailSubsystem;
import com.itep.hust.aimsgroup.controller.placeorder.mail.InvoiceMail;
import com.itep.hust.aimsgroup.subsystem.payment.Payment;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.payment.PaymentViewHandler;

public class PlaceOrderController {
    public void redirectToPayment(Invoice invoice, InvoiceDao invoiceDao, EmailSubsystem emailSubsystem, Payment payment) {
        payment.processTransaction(invoice.getTotal(), () -> {
            Screen.setScreen("/fxml/payment/payment-success.fxml", new PaymentViewHandler());

            invoiceDao.insert(invoice);

            emailSubsystem.sendMail(invoice.getDeliveryInfo().getEmail(), "AIMS Invoice", InvoiceMail.getContent(invoice));

            // Empty cart
            Cart.getInstance().empty();
        }, () -> {
            Screen.setScreen("/fxml/payment/payment-failure.fxml", new PaymentViewHandler());
        });
    }
}
