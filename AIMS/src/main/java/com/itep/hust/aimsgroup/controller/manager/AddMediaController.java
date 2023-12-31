package com.itep.hust.aimsgroup.controller.manager;

import com.itep.hust.aimsgroup.controller.manager.validator.detail_infor.InforBookValidator;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteMediaDao;

public class AddMediaController {
    private final InforBookValidator addMediaValidator;
    private final SqliteMediaDao sqliteMediaDao;

    public AddMediaController(InforBookValidator addMediaValidator, SqliteMediaDao sqliteMediaDao) {
        this.addMediaValidator = addMediaValidator;
        this.sqliteMediaDao = sqliteMediaDao;
    }

    public void addMedia(Media media) {
//        if(addMediaValidator.method check thong tin nhap vao)
        sqliteMediaDao.insert(media);
    }
}
