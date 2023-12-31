package com.itep.hust.aimsgroup.dao;
import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.deliveryinfo.RushDeliveryInfo;
import com.itep.hust.aimsgroup.service.dao.DeliveryInfoDao;
import com.itep.hust.aimsgroup.service.dao.sqlite.SqliteDeliveryInfoDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SqliteDeliveryInfoDaoTest {

    @Test
    void testInsertNormal() {
        DeliveryInfoDao deliveryInfoDao = new SqliteDeliveryInfoDao();

        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setName("Hi");
        deliveryInfo.setPhone("123");
        deliveryInfo.setAddress("123");
        deliveryInfo.setCity("ha noi");
        deliveryInfo.setInstruction("Handle with care");
        deliveryInfo.setEmail("example@example.com");

        DeliveryInfo deliveryInfo1 = deliveryInfoDao.insert(deliveryInfo);
        assertNotNull(deliveryInfo1.getId());
        System.out.println(deliveryInfo1.getId());
    }

    @Test
    void testInsertRush() {
        DeliveryInfoDao deliveryInfoDao = new SqliteDeliveryInfoDao();

        RushDeliveryInfo rushDeliveryInfo = new RushDeliveryInfo();
        rushDeliveryInfo.setRushTime(123);
        rushDeliveryInfo.setRushInstruction("Fast fast fast");

        rushDeliveryInfo.setName("Hi");
        rushDeliveryInfo.setPhone("123");
        rushDeliveryInfo.setAddress("123");
        rushDeliveryInfo.setCity("ha noi");
        rushDeliveryInfo.setInstruction("Handle with care");
        rushDeliveryInfo.setEmail("example@example.com");

        rushDeliveryInfo = (RushDeliveryInfo) deliveryInfoDao.insert(rushDeliveryInfo);

        assertNotNull(rushDeliveryInfo.getId());
        assertEquals(rushDeliveryInfo.getRushTime(), 123);
    }
}
