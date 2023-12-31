package com.itep.hust.aimsgroup.model.media;

import java.util.Objects;


public class Media {
    protected int id;
    protected String title;
    protected String category;
    protected int value;
    protected int price;
    protected int quantity;
    protected double weight;
    protected String imageURL;
    protected int rushDelivery;

    //Constructor
    public Media(int id, String title, String category, int price, int value, int quantity, double weight, String imageURL, int rushDelivery ) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.price = price;
        this.value = value;
        this.weight = weight;
        this.quantity = quantity;
        this.imageURL = imageURL;
        this.rushDelivery = rushDelivery;
    }

    public Media() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean isRushDelivery() {
        if(rushDelivery==1) {
            return true;
        } else {
            return false;
        }

    }
    public int getRushDelivery(){return this.rushDelivery;}

    public void setRushDelivery(int rushDelivery) {
        this.rushDelivery = rushDelivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Media media = (Media) o;
        return id == media.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
