package com.itep.hust.aimsgroup.service.dao.sqlite;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.invoice.Invoice;
import com.itep.hust.aimsgroup.model.order.Order;
import com.itep.hust.aimsgroup.service.dao.InvoiceDao;
import com.itep.hust.aimsgroup.service.database.SqliteDatabase;

import java.sql.*;
import java.util.List;

public class SqliteInvoiceDao implements InvoiceDao {
    @Override
    public List<Invoice> getAll() {
        return null;
    }

    @Override
    public Invoice get(Integer id) {
        return null;
    }

    @Override
    public Invoice insert(Invoice invoice) {
        Order order = invoice.getOrder();
        DeliveryInfo deliveryInfo = invoice.getDeliveryInfo();

        order = new SqliteOrderDao().insert(order);
        deliveryInfo = new SqliteDeliveryInfoDao().insert(deliveryInfo);

        Connection connection = SqliteDatabase.getConnection();

        String insertInvoiceQuery = """
            INSERT INTO Invoice (mediaTotal, mediaSubtotal, vat, shippingFee, total, orderId, deliveryInfoId)
            VALUES (?, ?, ?, ?, ?, ?, ?);
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertInvoiceQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDouble(1, invoice.getMediaTotal());
            preparedStatement.setDouble(2, invoice.getMediaSubtotal());
            preparedStatement.setDouble(3, invoice.getVat());
            preparedStatement.setDouble(4, invoice.getShippingFee());
            preparedStatement.setDouble(5, invoice.getTotal());
            preparedStatement.setInt(6, order.getId());
            preparedStatement.setInt(7, deliveryInfo.getId());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Invoice inserted successfully.");
                Integer generatedKeys = connection.prepareStatement("SELECT last_insert_rowid();").executeQuery().getInt(1);

                invoice.setId(generatedKeys);
                return invoice;
            } else {
                System.out.println("Failed to insert Invoice.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting into Invoice table", e);
        }

        return null;
    }

    @Override
    public Invoice update(Invoice invoice) {
        return null;
    }

    @Override
    public Invoice delete(Invoice invoice) {
        return null;
    }
}
