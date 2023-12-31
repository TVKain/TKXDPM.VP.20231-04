package com.itep.hust.aimsgroup.model.media.cd;

public class Track {
    int id;
    String name;

    public Track(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Track( String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
