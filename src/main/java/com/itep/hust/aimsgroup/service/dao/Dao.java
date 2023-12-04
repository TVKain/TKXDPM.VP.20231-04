package com.itep.hust.aimsgroup.service.dao;

import java.util.List;

/**
 * Dao pattern to access object in the database
 * @param <T>
 */
public interface Dao<T,U> {
    public List<T> getAll();
    public T get(U id);
    public T insert(T t);
    public T update(T t);
    public T delete(T t);
}
