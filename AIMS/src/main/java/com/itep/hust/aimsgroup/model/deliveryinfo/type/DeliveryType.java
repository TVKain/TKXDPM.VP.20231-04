package com.itep.hust.aimsgroup.model.deliveryinfo.type;

public enum DeliveryType {
    NORMAL("Normal"),
    RUSH("Rush");

    private final String displayName;

    DeliveryType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
