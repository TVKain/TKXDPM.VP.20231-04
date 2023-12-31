package com.itep.hust.aimsgroup.model.order;

import com.itep.hust.aimsgroup.model.media.Media;

import java.util.Map;

public class Order {
    private Map<Media, Integer> medias;
    private Integer id;

    private String status;

    public Order() {

    }


    public Map<Media, Integer> getMedias() {
        return medias;
    }

    public void setMedias(Map<Media, Integer> medias) {
        this.medias = medias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
