package com.itep.hust.aimsgroup.model.media.cd;

import com.itep.hust.aimsgroup.model.media.Media;

import java.util.Date;
import java.util.List;

public class CD extends Media {
    String artist;
    String recordLabel;
    String musicType;
    Date releasedDate;
    String CDCategory;
    List<Track> listTrack;

    public CD(int id, String title, String category, int price, int value, int quantity, double weight, String imageURL, String artist, String recordLabel, String musicType, String CDCategory, List<Track> listTrack) {
        super(id, title, category, price, value, quantity, weight, imageURL);
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.musicType = musicType;
        this.CDCategory = CDCategory;
        this.listTrack = listTrack;
    }
}
