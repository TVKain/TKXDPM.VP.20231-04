package com.itep.hust.aimsgroup.subsystem;

public interface Banking {
    void processTransaction(double money, Runnable onSuccess, Runnable onFailure);
}
