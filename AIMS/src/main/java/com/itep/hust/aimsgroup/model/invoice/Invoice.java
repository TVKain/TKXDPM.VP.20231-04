package com.itep.hust.aimsgroup.model.invoice;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.order.Order;

public class Invoice {
    private DeliveryInfo deliveryInfo;
    private Order order;

    public Invoice() {

    }

    public Invoice(DeliveryInfo deliveryInfo, Order order) {
        this.deliveryInfo = deliveryInfo;
        this.order = order;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}


