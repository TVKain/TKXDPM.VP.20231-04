package com.itep.hust.aimsgroup.model.deliveryinfo;

import lombok.ToString;

@ToString
public class DeliveryInfo {
    protected String name;
    protected String phone;
    protected String city;
    protected String address;
    protected String instruction;

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
