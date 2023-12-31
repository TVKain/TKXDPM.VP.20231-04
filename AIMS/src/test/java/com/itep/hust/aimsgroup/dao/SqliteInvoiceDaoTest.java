package com.itep.hust.aimsgroup.dao;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.invoice.Invoice;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.order.Order;
import com.itep.hust.aimsgroup.persistence.dao.InvoiceDao;
import com.itep.hust.aimsgroup.persistence.dao.sqlite.SqliteInvoiceDao;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SqliteInvoiceDaoTest {

    @Test
    void testInsert() {
        Invoice invoice = new Invoice();

        // Invoice
        DeliveryInfo deliveryInfo = new DeliveryInfo();
        deliveryInfo.setName("Hi");
        deliveryInfo.setPhone("123");
        deliveryInfo.setAddress("123");
        deliveryInfo.setCity("ha noi");
        deliveryInfo.setInstruction("Handle with care");
        deliveryInfo.setEmail("example@example.com");

        invoice.setDeliveryInfo(deliveryInfo);

        // Order
        Order order = new Order();
        
        order.setStatus("Processing");

        Map<Media, Integer> mediaMap = new HashMap<>();
        Media media = new Media();
        media.setId(38);
        mediaMap.put(media, 10);

        order.setMedias(mediaMap);

        invoice.setOrder(order);

        invoice.setShippingFee(100);
        invoice.setTotal(100);
        invoice.setMediaTotal(100);
        invoice.setVat(10);
        invoice.setMediaSubtotal(90);

        InvoiceDao invoiceDao = new SqliteInvoiceDao();
        invoiceDao.insert(invoice);
    }
}
