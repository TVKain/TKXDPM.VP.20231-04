package com.itep.hust.aimsgroup.model.invoice;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.order.Order;

public class Invoice {
    private Integer id;
    private double mediaTotal;
    private double mediaSubtotal;
    private double vat;
    private double shippingFee;

    private double total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

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

    public double getMediaTotal() {
        return mediaTotal;
    }

    public void setMediaTotal(double mediaTotal) {
        this.mediaTotal = mediaTotal;
    }

    public double getMediaSubtotal() {
        return mediaSubtotal;
    }

    public void setMediaSubtotal(double mediaSubtotal) {
        this.mediaSubtotal = mediaSubtotal;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(double shippingFee) {
        this.shippingFee = shippingFee;
    }
}


