package com.itep.hust.aimsgroup.model.deliveryinfo;

import lombok.ToString;

@ToString
public class DeliveryInfo {
    protected String name;
    protected String phone;
    protected String city;

    protected String email;
    protected String address;
    protected String instruction;

    public DeliveryInfo() {

    }

    public DeliveryInfo(String name, String phone, String city, String email, String address, String instruction) {
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.email = email;
        this.address = address;
        this.instruction = instruction;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getInstruction() {
        return instruction;
    }
}
