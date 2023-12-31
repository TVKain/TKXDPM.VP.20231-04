package com.itep.hust.aimsgroup.service.dao.sqlite;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.order.Order;
import com.itep.hust.aimsgroup.model.order.OrderMedia;
import com.itep.hust.aimsgroup.service.dao.OrderDao;
import com.itep.hust.aimsgroup.service.dao.OrderMediaDao;
import com.itep.hust.aimsgroup.service.database.SqliteDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class SqliteOrderDao implements OrderDao {
    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public Order get(Integer id) {
        return null;
    }

    @Override
    public Order insert(Order order) {
        String orderInsert = """
                INSERT INTO "Order"("status") VALUES (?);""";

        Map<Media, Integer> mediaMap = order.getMedias();

        Connection connection = SqliteDatabase.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(orderInsert);
            preparedStatement.setString(1, order.getStatus());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Insert order success");
            }

            int orderId = connection.prepareStatement("SELECT last_insert_rowid();").executeQuery().getInt(1);
            order.setId(orderId);

            for (Map.Entry<Media, Integer> mediaEntry : mediaMap.entrySet()) {
                OrderMedia orderMedia = new OrderMedia();
                orderMedia.setQuantity(mediaEntry.getValue());
                orderMedia.setOrderId(orderId);
                orderMedia.setMediaId(mediaEntry.getKey().getId());

                SqliteOrderMediaDao sqliteOrderMediaDao = new SqliteOrderMediaDao();
                sqliteOrderMediaDao.insert(orderMedia);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return order;
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public Order delete(Order order) {
        return null;
    }
}
