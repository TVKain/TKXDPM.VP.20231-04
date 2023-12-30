package com.itep.hust.aimsgroup.service.dao.sqlite;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.deliveryinfo.RushDeliveryInfo;
import com.itep.hust.aimsgroup.service.dao.DeliveryInfoDao;
import com.itep.hust.aimsgroup.service.database.SqliteDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SqliteDeliveryInfoDao implements DeliveryInfoDao {
    @Override
    public List<DeliveryInfo> getAll() {
        return null;
    }

    @Override
    public DeliveryInfo get(Integer id) {
        return null;
    }

    @Override
    public DeliveryInfo insert(DeliveryInfo deliveryInfo) {
        try (Connection connection = SqliteDatabase.getConnection()) {

            // Insert data into DeliveryInfo table
            String insertDeliveryInfoSql = "INSERT INTO DeliveryInfo (name, phone, city, email, address, instruction) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement deliveryInfoStatement = connection.prepareStatement(insertDeliveryInfoSql)) {
                deliveryInfoStatement.setString(1, deliveryInfo.getName());
                deliveryInfoStatement.setString(2, deliveryInfo.getPhone());
                deliveryInfoStatement.setString(3, deliveryInfo.getCity());
                deliveryInfoStatement.setString(4, deliveryInfo.getEmail());
                deliveryInfoStatement.setString(5, deliveryInfo.getAddress());
                deliveryInfoStatement.setString(6, deliveryInfo.getInstruction());

                deliveryInfoStatement.executeUpdate();
            }

            if (deliveryInfo instanceof RushDeliveryInfo rushDeliveryInfo) {
                String insertRushDeliveryInfoSql = "INSERT INTO RushDeliveryInfo (id, rushInstruction, rushTime) " +
                        "VALUES (last_insert_rowid(), ?, ?)";

                try (PreparedStatement rushDeliveryInfoStatement = connection.prepareStatement(insertRushDeliveryInfoSql)) {
                    rushDeliveryInfoStatement.setString(1, rushDeliveryInfo.getRushInstruction());
                    rushDeliveryInfoStatement.setInt(2, rushDeliveryInfo.getRushTime());

                    rushDeliveryInfoStatement.executeUpdate();
                }
            }


        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return deliveryInfo;
    }

    @Override
    public DeliveryInfo update(DeliveryInfo deliveryInfo) {
        return null;
    }

    @Override
    public DeliveryInfo delete(DeliveryInfo deliveryInfo) {
        return null;
    }
}
