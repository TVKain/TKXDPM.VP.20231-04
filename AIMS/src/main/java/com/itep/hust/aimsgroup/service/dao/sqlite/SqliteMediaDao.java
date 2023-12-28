package com.itep.hust.aimsgroup.service.dao.sqlite;

import com.itep.hust.aimsgroup.model.admin.Admin;
import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.service.dao.Dao;
import com.itep.hust.aimsgroup.service.database.SqliteDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * SqliteMediaDao class implements Dao interface uses sqlite database
 * @author TVKain
 */
public class SqliteMediaDao implements Dao<Media, Integer> {

    /**
     * Get all media from db
     * @return list media
     * @author KhanhND
     */
    @Override
    public List<Media> getAll() {
        List<Media> listMedia = new ArrayList<>();
        String query = "select * from media";
        String query_book = "SELECT * FROM media INNER JOIN book ON media.id = book.id";
        String query_dvd = "SELECT * FROM media INNER JOIN dvd ON media.id = dvd.id";
        String query_cd = "SELECT * FROM media INNER JOIN book ON media.id = cd.id INNER JOIN track ON cd.id = track.CD_id";
        Connection connection = SqliteDatabase.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listMedia.add(new Media(rs.getInt("id"), rs.getString("title"), rs.getString("category") ,rs.getInt("price") * 1000, rs.getInt("value") * 1000,
                        rs.getInt("quantity"), rs.getDouble("weight"), rs.getString("imageURL"), rs.getInt("rushDelivery")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listMedia;
    }

    @Override
    public Media get(Integer id) {
        return null;
    }

    @Override
    public Media insert(Media media) {
        Connection connection = SqliteDatabase.getConnection();
        String insertQuery = "INSERT INTO media (id, title, category, price, value, quantity, weight, imageURL, rushDelivery) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, media.getId());
            preparedStatement.setString(2, media.getTitle());
            preparedStatement.setString(3, media.getCategory());
            preparedStatement.setInt(4, media.getPrice());
            preparedStatement.setInt(5, media.getValue());
            preparedStatement.setInt(6, media.getQuantity());
            preparedStatement.setDouble(7, media.getWeight());
            preparedStatement.setString(8, media.getImageURL());
            preparedStatement.setInt(9, media.getRushDelivery());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Media update(Media media) {
        return null;
    }

    @Override
    public Media delete(Media media) {
        return null;
    }
}
