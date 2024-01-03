package com.itep.hust.aimsgroup.dao.sqlite;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.persistence.dao.Dao;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteMediaDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SqliteMediaDaoTest {
    @Test
    public void testGetAll() {
        Dao<Media, Integer> mediaIntegerDao =  new SqliteMediaDao();
        List<Media> mediaList = mediaIntegerDao.getAll();
        assertFalse(mediaList.isEmpty());
    }
}
