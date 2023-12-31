package com.itep.hust.aimsgroup.dao;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.order.Order;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteOrderDao;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class SqliteOrderDaoTest {

    @Test
    void testInsert() {
        Order order = new Order();

        order.setId(2);
        order.setStatus("Processing");

        Map<Media, Integer> mediaMap = new HashMap<>();
        Media media = new Media();
        media.setId(38);
        mediaMap.put(media, 10);

        order.setMedias(mediaMap);

        SqliteOrderDao sqliteOrderDao = new SqliteOrderDao();
        sqliteOrderDao.insert(order);
    }
}
