package com.itep.hust.aimsgroup.dao.sqlite;

import com.itep.hust.aimsgroup.model.order.OrderMedia;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteOrderMediaDao;
import org.junit.jupiter.api.Test;

public class SqliteOrderMediaDaoTest {

    @Test
    void testInsert() {
        // Assume there's an order with id = 1 exist in the database already
        SqliteOrderMediaDao sqliteOrderMediaDao = new SqliteOrderMediaDao();

        OrderMedia orderMedia = new OrderMedia();
        orderMedia.setMediaId(38);
        orderMedia.setOrderId(1);
        orderMedia.setQuantity(4);

        sqliteOrderMediaDao.insert(orderMedia);
    }
}
