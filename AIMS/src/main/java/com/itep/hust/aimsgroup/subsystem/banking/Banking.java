package com.itep.hust.aimsgroup.subsystem.banking;

public interface Banking {
    void processTransaction(double money, Runnable onSuccess, Runnable onFailure);
}
