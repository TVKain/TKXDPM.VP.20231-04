package com.itep.hust.aimsgroup.controller.manager;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteMediaDao;
import javafx.scene.control.Label;

import java.util.List;

public class ManagerController {
    private final SqliteMediaDao sqliteMediaDao;


    public ManagerController(SqliteMediaDao sqliteMediaDao) {
        this.sqliteMediaDao = sqliteMediaDao;
    }
    public List<Media> getAllMedia() {
        return sqliteMediaDao.getAll();
    }

    public void deleteMedia(Media media){
//        if gọi validator check thao tác
        sqliteMediaDao.delete(media);
    }

    public void updateMedia(Media media) {
//        gọi check validator thao tác -> gọi tiếp validator info nhập vào
        sqliteMediaDao.update(media);
    }

    public void addMedia(Media media) {
//        gọi validator info nhập
        sqliteMediaDao.insert(media);
    }
}
