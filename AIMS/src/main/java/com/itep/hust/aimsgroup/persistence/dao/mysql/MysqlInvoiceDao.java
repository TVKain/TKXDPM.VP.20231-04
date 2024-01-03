package com.itep.hust.aimsgroup.persistence.dao.mysql;

import com.itep.hust.aimsgroup.model.deliveryinfo.DeliveryInfo;
import com.itep.hust.aimsgroup.model.invoice.Invoice;
import com.itep.hust.aimsgroup.model.order.Order;
import com.itep.hust.aimsgroup.persistence.dao.InvoiceDao;
import com.itep.hust.aimsgroup.persistence.database.MysqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MysqlInvoiceDao implements InvoiceDao {
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

        order = new MysqlOrderDao().insert(order);
        deliveryInfo = new MysqlDeliveryInfoDao().insert(deliveryInfo);

        Connection connection = MysqlDatabase.getConnection();

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
                Integer generatedKeys = connection.prepareStatement("SELECT LAST_INSERT_ID();").executeQuery().getInt(1);

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
