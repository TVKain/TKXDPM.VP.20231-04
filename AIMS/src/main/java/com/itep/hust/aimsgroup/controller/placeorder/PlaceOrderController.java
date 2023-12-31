package com.itep.hust.aimsgroup.controller.placeorder;

import com.itep.hust.aimsgroup.model.invoice.Invoice;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteInvoiceDao;
import com.itep.hust.aimsgroup.subsystem.email.EmailService;
import com.itep.hust.aimsgroup.controller.placeorder.mail.InvoiceMail;
import com.itep.hust.aimsgroup.subsystem.banking.Banking;
import com.itep.hust.aimsgroup.subsystem.banking.vnpay.VNPaySubsystem;
import com.itep.hust.aimsgroup.util.Screen;
import com.itep.hust.aimsgroup.view.payment.PaymentViewHandler;

public class PlaceOrderController {
    public void redirectToPayment(Invoice invoice, EmailService emailService) {
        Banking banking = new VNPaySubsystem();

        banking.processTransaction(invoice.getTotal(), () -> {
            Screen.setScreen("/fxml/payment/payment-success.fxml", new PaymentViewHandler());
            new SqliteInvoiceDao().insert(invoice);
            emailService.sendMail(invoice.getDeliveryInfo().getEmail(), "AIMS Invoice", InvoiceMail.getContent(invoice));
        }, () -> {
            Screen.setScreen("/fxml/payment/payment-failure.fxml", new PaymentViewHandler());
        });
    }
}
