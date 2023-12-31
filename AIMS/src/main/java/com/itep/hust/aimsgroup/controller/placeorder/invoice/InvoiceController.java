package com.itep.hust.aimsgroup.controller.placeorder.invoice;

import com.itep.hust.aimsgroup.model.cart.Cart;
import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.invoice.Invoice;
import com.itep.hust.aimsgroup.model.order.Order;

public class InvoiceController {
    public Invoice createInvoice(DeliveryInfo deliveryInfo, double shippingFee) {
        Invoice invoice = new Invoice();
        invoice.setVat(10);
        invoice.setShippingFee(shippingFee);

        invoice.setMediaSubtotal(Cart.getInstance().getTotalPrice());
        invoice.setMediaTotal(invoice.getMediaSubtotal() * (1 + invoice.getVat() / 100));
        invoice.setTotal(invoice.getMediaTotal() + invoice.getShippingFee());

        invoice.setDeliveryInfo(deliveryInfo);

        Order order = new Order();
        order.setStatus("processing");
        order.setMedias(Cart.getInstance().getMedias());

        invoice.setOrder(order);

        return invoice;
    }

}
