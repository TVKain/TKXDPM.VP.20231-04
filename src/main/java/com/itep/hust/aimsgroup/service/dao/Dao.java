package com.itep.hust.aimsgroup.service.dao;

import java.util.List;

/**
 * Dao pattern to access object in the database
 * @param <T>
 */
public interface Dao<T> {
    List<T> getAll();
    T get(Integer id);
    T insert(T t);
    T update(T t);
    T delete(T t);
}
