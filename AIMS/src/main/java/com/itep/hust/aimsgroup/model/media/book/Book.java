package com.itep.hust.aimsgroup.model.media.book;

import com.itep.hust.aimsgroup.model.media.Media;

import java.util.Date;

public class Book extends Media {
    String author;
    String coverType;
    String publisher;
    Date publishDate;
    int numOfPages;
    String language;
    String bookCategory;

    public Book(int id, String title, String category, int price, int value, int quantity, double weight, String imageURL, String author, String coverType, String publisher, Date publishDate) {
        super(id, title, category, price, value, quantity, weight, imageURL);
        this.author = author;
        this.coverType = coverType;
        this.publisher = publisher;
        this.publishDate = publishDate;
    }

}