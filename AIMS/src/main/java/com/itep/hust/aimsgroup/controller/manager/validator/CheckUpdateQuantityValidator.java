package com.itep.hust.aimsgroup.controller.manager.validator;

import com.itep.hust.aimsgroup.controller.manager.validator.detail_infor.InforBookValidator;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteMediaDao;

import java.time.LocalDate;

public class CheckUpdateQuantityValidator {
    private static int quantity;
    private static LocalDate localDate;
    private final InforBookValidator addMediaValidator;
    private final SqliteMediaDao sqliteMediaDao;

    public CheckUpdateQuantityValidator(InforBookValidator addMediaValidator, SqliteMediaDao sqliteMediaDao) {
        this.addMediaValidator = addMediaValidator;
        this.sqliteMediaDao = sqliteMediaDao;
    }
    public boolean checkConditionUpdateMedia(Media media) {
//        if(Gọi hàm logic để check localDate và quanti trong ngày)
        return true;

    }
}
