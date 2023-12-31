package com.itep.hust.aimsgroup.model.media.cd;

import com.itep.hust.aimsgroup.model.media.Media;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CD extends Media {
    String artist;
    String recordLabel;
    String musicType;
    Date releasedDate;
    String CDCategory;
    List<Track> listTrack = new ArrayList<Track>();

    public CD(int id, String title, String category, int price, int value, int quantity, double weight, String imageURL, String artist, String recordLabel, String musicType, String CDCategory, int rushDelivery) {
        super(id, title, category, price, value, quantity, weight, imageURL, rushDelivery);
        this.artist = artist;
        this.recordLabel = recordLabel;
        this.musicType = musicType;
        this.CDCategory = CDCategory;
    }

    public void addTrack(Track... listTrack) {
        for (Track track: listTrack) {
            this.listTrack.add(track);
        }
    }

    public String getArtist() {
        return artist;
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public String getMusicType() {
        return musicType;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public String getCDCategory() {
        return CDCategory;
    }

    public List<Track> getListTrack() {
        return listTrack;
    }
}
