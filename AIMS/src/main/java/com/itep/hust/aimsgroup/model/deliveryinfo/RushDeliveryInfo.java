package com.itep.hust.aimsgroup.model.deliveryinfo;

public class RushDeliveryInfo extends DeliveryInfo {
    private String rushInstruction;
    private Integer rushTime;

    public String getRushInstruction() {
        return rushInstruction;
    }

    public void setRushInstruction(String rushInstruction) {
        this.rushInstruction = rushInstruction;
    }

    public Integer getRushTime() {
        return rushTime;
    }

    public void setRushTime(Integer rushTime) {
        this.rushTime = rushTime;
    }
}
