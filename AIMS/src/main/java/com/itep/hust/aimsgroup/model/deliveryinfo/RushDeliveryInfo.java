package com.itep.hust.aimsgroup.model.deliveryinfo;

import lombok.ToString;

@ToString(callSuper = true)
public class RushDeliveryInfo extends DeliveryInfo {
    private String rushInstruction;
    private Integer rushTime;

    public RushDeliveryInfo() {

    }

    public RushDeliveryInfo(String rushInstruction, Integer rushTime) {
        this.rushInstruction = rushInstruction;
        this.rushTime = rushTime;
    }

    public RushDeliveryInfo(String name, String phone, String city, String email, String address, String instruction, String rushInstruction, Integer rushTime) {
        super(name, phone, city, email, address, instruction);
        this.rushInstruction = rushInstruction;
        this.rushTime = rushTime;
    }

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
