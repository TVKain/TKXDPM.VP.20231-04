package com.itep.hust.aimsgroup.subsystem.payment;

public interface Payment {
    void processTransaction(double money, Runnable onSuccess, Runnable onFailure);
}
