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

    public boolean deleteMedia(Media media){
//        if gọi validator check thao tác
        sqliteMediaDao.delete(media);
        return true;
    }

    public boolean updateMedia(Media media) {
//        gọi check validator thao tác -> gọi tiếp validator info nhập vào
        sqliteMediaDao.update(media);
        return true;
    }

    public boolean addMedia(Media media) {
//        gọi validator info nhập
        sqliteMediaDao.insert(media);
        return true;
    }
}
