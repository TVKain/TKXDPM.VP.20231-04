package com.itep.hust.aimsgroup.model.media.dvd;

import com.itep.hust.aimsgroup.model.media.Media;

import java.util.Date;

public class DVD extends Media {
    String discType;
    String director;
    String runtime;
    String studio;
    String subtitles;
    String language;
    Date releasedDate; //Null
    String DVDCategory; // NULL

    public DVD(int id, String title, String category, int price, int value, int quantity, double weight, String imageURL, String discType, String director, String runtime, String studio, String language, String subtitles, int rushDelivery) {
        super(id, title, category, price, value, quantity, weight, imageURL, rushDelivery);
        this.discType = discType;
        this.director = director;
        this.runtime = runtime;
        this.studio = studio;
        this.language = language;
        this.subtitles = subtitles;
    }

    public String getDiscType() {
        return discType;
    }

    public String getDirector() {
        return director;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getStudio() {
        return studio;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public String getLanguage() {
        return language;
    }
}
