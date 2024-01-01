package com.itep.hust.aimsgroup.controller.placeorder;

import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.invoice.Invoice;
import com.itep.hust.aimsgroup.persistence.dao.InvoiceDao;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteInvoiceDao;
import com.itep.hust.aimsgroup.subsystem.email.EmailSubsystem;
import com.itep.hust.aimsgroup.controller.placeorder.mail.InvoiceMail;
import com.itep.hust.aimsgroup.subsystem.banking.Banking;
import com.itep.hust.aimsgroup.subsystem.banking.vnpay.VNPaySubsystem;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.payment.PaymentViewHandler;

public class PlaceOrderController {
    public void redirectToPayment(Invoice invoice, EmailSubsystem emailSubsystem, InvoiceDao invoiceDao) {
        Banking banking = new VNPaySubsystem();

        banking.processTransaction(invoice.getTotal(), () -> {
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
