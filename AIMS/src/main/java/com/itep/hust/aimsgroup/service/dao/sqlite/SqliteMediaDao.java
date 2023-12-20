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
        Connection connection = SqliteDatabase.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listMedia.add(new Media(rs.getInt("id"), rs.getString("title"), (int) rs.getInt("price") * 1000,
                        rs.getInt("quantity"), rs.getDouble("weight"), rs.getString("imageURL")));
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
