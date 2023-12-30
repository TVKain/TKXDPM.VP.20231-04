package com.itep.hust.aimsgroup.service.dao.sqlite;

import com.itep.hust.aimsgroup.model.media.Media;
import com.itep.hust.aimsgroup.model.media.book.Book;
import com.itep.hust.aimsgroup.model.media.cd.CD;
import com.itep.hust.aimsgroup.model.media.cd.Track;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String query_book = "SELECT * FROM media INNER JOIN book ON media.id = book.id";
        String query_dvd = "SELECT * FROM media INNER JOIN dvd ON media.id = dvd.id";
        String query_cd = "SELECT * FROM media INNER JOIN cd ON media.id = cd.id";
        String query_track = "SELECT * FROM track";
        Connection connection = SqliteDatabase.getConnection();
        try {
            // Get all book
            PreparedStatement preparedStatement = connection.prepareStatement(query_book);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                listMedia.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("category") ,rs.getInt("price") , rs.getInt("value") ,
                        rs.getInt("quantity"), rs.getDouble("weight"), rs.getString("imageURL"), rs.getString("author"), rs.getString("cover_type"), rs.getString("publisher"),  LocalDate.parse(rs.getString("publish_date"), formatter), rs.getInt("rushDelivery")));
            }
            // Get all dvd
            preparedStatement = connection.prepareStatement(query_dvd);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listMedia.add(new DVD(rs.getInt("id"), rs.getString("title"), rs.getString("category") ,rs.getInt("price") , rs.getInt("value") ,
                        rs.getInt("quantity"), rs.getDouble("weight"), rs.getString("imageURL"), rs.getString("disc_type"), rs.getString("director"), rs.getString("runtime"),  rs.getString("studio"), rs.getString("language"), rs.getString("subtitle"), rs.getInt("rushDelivery") ));
            }
            // Get all cd
            preparedStatement = connection.prepareStatement(query_cd);
            rs = preparedStatement.executeQuery();
            Map<Integer,CD> mapCD = new HashMap<Integer,CD>();
            while (rs.next()) {
                CD newCD = new CD(rs.getInt("id"), rs.getString("title"), rs.getString("category") ,rs.getInt("price") , rs.getInt("value") ,
                        rs.getInt("quantity"), rs.getDouble("weight"), rs.getString("imageURL"), rs.getString("artist"), rs.getString("record_label"), rs.getString("music_type"),   rs.getString("category_cd"), rs.getInt("rushDelivery"));
                mapCD.put(rs.getInt("id"), newCD);
            }

            if(mapCD.size() > 0) {
                //Get track
                preparedStatement = connection.prepareStatement(query_track);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    Track track = new Track(rs.getInt("id"), rs.getString("name"));
                    mapCD.get(rs.getInt("CD_id")).addTrack(track);
                }
                // Chuyển đổi giá trị từ HashMap thành List
                List<CD> listCD = new ArrayList<>(mapCD.values());
                listMedia.addAll(listCD);
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
            insertQuery2 = "INSERT INTO book (id, author, cover_type, publisher, publish_date) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery2);
                Book book = (Book) media;
                preparedStatement.setInt(1, book.getId());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setString(3, book.getCoverType());
                preparedStatement.setString(4, book.getPublisher());
                preparedStatement.setString(5, book.getPublishDate().toString());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (media instanceof DVD) {
            insertQuery2 = "INSERT INTO dvd (id, disc_type, director, runtime, studio, language, subtitle) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery2);
                DVD dvd= (DVD) media;
                preparedStatement.setInt(1, dvd.getId());
                preparedStatement.setString(2, dvd.getDiscType());
                preparedStatement.setString(3, dvd.getDirector());
                preparedStatement.setString(4, dvd.getRuntime());
                preparedStatement.setString(5, dvd.getStudio());
                preparedStatement.setString(6, dvd.getLanguage());
                preparedStatement.setString(7, dvd.getSubtitles());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (media instanceof CD) {
            insertQuery2 = "INSERT INTO cd (id, artist, music_type, record_label, category_cd) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery2);
                CD cd= (CD) media;
                preparedStatement.setInt(1, cd.getId());
                preparedStatement.setString(2, cd.getArtist());
                preparedStatement.setString(3, cd.getMusicType());
                preparedStatement.setString(4, cd.getRecordLabel());
                preparedStatement.setString(5, cd.getCDCategory());
                preparedStatement.executeUpdate();
                List<Track> listTrack = cd.getListTrack();
                for (Track track : listTrack) {
                    String insertQuery3 = "INSERT INTO track (name, CD_id) VALUES ( ?, ?)";
                    preparedStatement = connection.prepareStatement(insertQuery3);
                    preparedStatement.setString(1, track.getName());
                    preparedStatement.setInt(2, cd.getId());
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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

        Connection connection = SqliteDatabase.getConnection();

        String updateQuery1 = "UPDATE media SET title = ?, category = ?, price = ?, value = ?, quantity = ?, weight = ?, " +
                "imageURL = ?, rushDelivery = ? WHERE id = ?";
        String updateQuery2 = "";
        if (media instanceof Book) {
            updateQuery2 = "UPDATE book " +
                    "SET author = ?, cover_type = ?, publisher = ?, publish_date = ? " +
                    "WHERE id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery2);
                Book book = (Book) media;
                preparedStatement.setInt(5, book.getId());
                preparedStatement.setString(1, book.getAuthor());
                preparedStatement.setString(2, book.getCoverType());
                preparedStatement.setString(3, book.getPublisher());
                preparedStatement.setString(4, book.getPublishDate().toString());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (media instanceof DVD) {
            updateQuery2 = "UPDATE dvd SET disc_type = ?, director = ?, runtime = ?, studio = ?, language = ?, subtitle = ? " +
                    "WHERE id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery2);
                DVD dvd= (DVD) media;
                preparedStatement.setInt(7, dvd.getId());
                preparedStatement.setString(1, dvd.getDiscType());
                preparedStatement.setString(2, dvd.getDirector());
                preparedStatement.setString(3, dvd.getRuntime());
                preparedStatement.setString(4, dvd.getStudio());
                preparedStatement.setString(5, dvd.getLanguage());
                preparedStatement.setString(6, dvd.getSubtitles());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (media instanceof CD) {
            updateQuery2 = "UPDATE cd SET artist = ?, music_type = ?, record_label = ?, category_cd = ? WHERE id = ? ";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery2);
                CD cd= (CD) media;
                preparedStatement.setInt(5, cd.getId());
                preparedStatement.setString(1, cd.getArtist());
                preparedStatement.setString(2, cd.getMusicType());
                preparedStatement.setString(3, cd.getRecordLabel());
                preparedStatement.setString(4, cd.getCDCategory());
                preparedStatement.executeUpdate();
                List<Track> listTrack = cd.getListTrack();
                for (Track track : listTrack) {
                    String insertQuery3 = "INSERT INTO track (name, CD_id) VALUES ( ?, ?)";
                    preparedStatement = connection.prepareStatement(insertQuery3);
                    preparedStatement.setString(1, track.getName());
                    preparedStatement.setInt(2, cd.getId());
                    preparedStatement.executeUpdate();
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery1);
            preparedStatement.setInt(9, media.getId());
            preparedStatement.setString(1, media.getTitle());
            preparedStatement.setString(2, media.getCategory());
            preparedStatement.setInt(3, media.getPrice());
            preparedStatement.setInt(4, media.getValue());
            preparedStatement.setInt(5, media.getQuantity());
            preparedStatement.setDouble(6, media.getWeight());
            preparedStatement.setString(7, media.getImageURL());
            preparedStatement.setInt(8, media.getRushDelivery());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void deleteTrack(List<Track> listTrack) {
        Connection connection = SqliteDatabase.getConnection();
        String deleteQuery = "DELETE FROM track where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            for (Track track: listTrack) {
                preparedStatement.setInt(1,track.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw  new RuntimeException();
        }
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
            String query_delete_track = "DELETE FROM track WHERE CD_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query_delete_track);
                preparedStatement.setInt(1, media.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

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
