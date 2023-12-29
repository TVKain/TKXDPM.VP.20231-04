package com.itep.hust.aimsgroup.model.media.book;

import com.itep.hust.aimsgroup.model.media.Media;

import java.time.LocalDate;
import java.util.Date;

public class Book extends Media {
    String author;
    String coverType;
    String publisher;
    LocalDate publishDate;
    int numOfPages;
    String language;
    String bookCategory;

    public Book(int id, String title, String category, int price, int value, int quantity, double weight, String imageURL, String author, String coverType, String publisher, LocalDate publishDate, int rushDelivery) {
        super(id, title, category, price, value, quantity, weight, imageURL, rushDelivery);
        this.author = author;
        this.coverType = coverType;
        this.publisher = publisher;
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getCoverType() {
        return coverType;
    }

    public String getPublisher() {
        return publisher;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public String getLanguage() {
        return language;
    }

    public String getBookCategory() {
        return bookCategory;
    }
}
