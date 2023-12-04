package com.itep.hust.aimsgroup.dao;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.service.dao.Dao;
import com.itep.hust.aimsgroup.service.dao.SqliteMediaDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class MediaDaoTest {
    @Test
    public void testGetAll() {
        Dao<Media, Integer> mediaIntegerDao =  new SqliteMediaDao();
        List<Media> mediaList = mediaIntegerDao.getAll();
        assertFalse(mediaList.isEmpty());
    }
}
