package com.itep.hust.aimsgroup.payment;

public class PaymentTransaction {
    private String transactionContent;
    private String createAt;
    private int orderId;

    public PaymentTransaction(String transactionContent, String createAt, int orderId) {
        this.orderId = orderId;
        this.transactionContent = transactionContent;
        this.createAt = createAt;
    }

    public PaymentTransaction(String transactionContent, String createAt) {
        this.transactionContent = transactionContent;
        this.createAt = createAt;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public String getCreateAt() {
        return createAt;
    }
}
