package com.itep.hust.aimsgroup.model.order;

import java.util.Date;

public class DeliveryInfo {
    String name;
    String province;
    String phone;
    String address;
    String instruction;
    Date timeReceive;

    public DeliveryInfo(String name, String province, String address, String instruction, Date timeReceive) {
        this.name = name;
        this.province = province;
        this.address = address;
        this.instruction = instruction;
        this.timeReceive = timeReceive;
    }
    public DeliveryInfo(String name, String phone, String province, String address, String instruction) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.instruction = instruction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Date getTimeReceive() {
        return timeReceive;
    }

    public void setTimeReceive(Date timeReceive) {
        this.timeReceive = timeReceive;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
