package com.itep.hust.aimsgroup.persistence.dao.sqlite;

import com.itep.hust.aimsgroup.model.order.OrderMedia;
import com.itep.hust.aimsgroup.persistence.dao.OrderMediaDao;
import com.itep.hust.aimsgroup.persistence.database.SqliteDatabase;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqliteOrderMediaDao implements OrderMediaDao {
    @Override
    public List<OrderMedia> getAll() {
        return null;
    }

    @Override
    public OrderMedia get(Pair<Integer, Integer> id) {
        return null;
    }

    @Override
    public OrderMedia insert(OrderMedia orderMedia) {
        String insertQuery = """
                INSERT INTO OrderMedia (orderId, mediaId, quantity)
                VALUES (?, ?, ?)
                """;

        try (Connection connection = SqliteDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setInt(1, orderMedia.getOrderId());
            preparedStatement.setInt(2, orderMedia.getMediaId());
            preparedStatement.setInt(3, orderMedia.getQuantity());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("OrderMedia inserted successfully.");
                return orderMedia;
            } else {
                System.out.println("Failed to insert OrderMedia.");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return null;
    }

    @Override
    public OrderMedia update(OrderMedia orderMedia) {
        return null;
    }

    @Override
    public OrderMedia delete(OrderMedia orderMedia) {
        return null;
    }

    @Override
    public List<OrderMedia> getByOrderId(Integer id) {
        String query = "SELECT * FROM OrderMedia WHERE orderId = ?";

        List<OrderMedia> orderMediaList = new ArrayList<>();
        try (Connection connection = SqliteDatabase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderMedia orderMedia = new OrderMedia();
                orderMedia.setOrderId(id);
                orderMedia.setMediaId(resultSet.getInt("mediaId"));
                orderMedia.setQuantity(resultSet.getInt("quantity"));
                orderMediaList.add(orderMedia);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return orderMediaList;
    }
}
