package com.itep.hust.aimsgroup.service.dao;

import com.itep.hust.aimsgroup.model.media.Media;

import java.util.List;

/**
 * Dao pattern to access object in the database
 *  * @param <T>
 *      Analyze cohesion: Trong dao, sẽ cần những lớp Dao như hiện tại có 2 lớp AdminDAO và Sqlite DAO để thực hiện nhiệm
 *      vụ liên quan đến database. Nêu chỉ tạo 1 lớp là Dao và có các fuction liên quan đến databse của admin và media thì các hàm này
 *      không có sự liên quan. Nên nhóm đã tạo 1 interface và 2 lớp riêng để tránh logic cohesion
 * @author: KhanhND
 */

/**
 * Phân tích SOLID
 * Bởi hầu hết các cơ sở dữ liêu đều cần các phương thức get insert update delete vì vậy với tính I trong nguyên lý SOLID
 * các function này sẽ được nhóm với nhau thành 1 interface để các entity trong database dùng chỉ cần impliment
 * @author KhanhND
 */
public interface Dao<T,U> {
    public List<T> getAll();
    public T get(U id);
    public T insert(T t);
    public T update(T t);
    public T delete(T t);
}
