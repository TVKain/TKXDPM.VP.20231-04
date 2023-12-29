package com.itep.hust.aimsgroup.service.dao.sqlite;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.media.book.Book;
import com.itep.hust.aimsgroup.model.media.cd.CD;
import com.itep.hust.aimsgroup.model.media.dvd.DVD;
import com.itep.hust.aimsgroup.service.dao.Dao;
import com.itep.hust.aimsgroup.service.database.SqliteDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        //String query_cd = "SELECT * FROM media INNER JOIN book ON media.id = cd.id INNER JOIN track ON cd.id = track.CD_id";
        Connection connection = SqliteDatabase.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query_book);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                listMedia.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("category") ,rs.getInt("price") * 1000, rs.getInt("value") * 1000,
                        rs.getInt("quantity"), rs.getDouble("weight"), rs.getString("imageURL"), rs.getString("author"), rs.getString("cover_type"), rs.getString("publisher"),  LocalDate.parse(rs.getString("publish_date"), formatter), rs.getInt("rushDelivery")));
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

        String insertQuery1 = "INSERT INTO media (id, title, category, price, value, quantity, weight, imageURL, rushDelivery) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String insertQuery2 = "";
        if (media instanceof Book) {
            insertQuery2 = "INSERT INTO book (id, author, cover_type, publisher, publisher_date) " +
                    "VALUES (?, ?, ?, ?, ?)";
        } else if (media instanceof DVD) {

        } else if (media instanceof CD) {

        }


        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery1);
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
        Connection connection = SqliteDatabase.getConnection();
        String typeOfMedia = "";
        if (media instanceof Book) {
            typeOfMedia = "book";
        } else if (media instanceof DVD) {
            typeOfMedia = "dvd";
        } else if (media instanceof CD) {
            typeOfMedia = "cd";
        }
        String deleteQuery1 = "DELETE FROM media WHERE id = ?";
        String deleteQuery2 = "DELETE FROM " + typeOfMedia + " WHERE id = ?";
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(deleteQuery1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(deleteQuery2);
            preparedStatement1.setInt(1, media.getId());
            preparedStatement2.setInt(1, media.getId());
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
