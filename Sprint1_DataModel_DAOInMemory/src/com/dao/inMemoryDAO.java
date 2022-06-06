package com.dao;

import java.util.List;

public interface inMemoryDAO<T> {
    T insertOrUpdate(T t);
    List<T> findAll();
    void deleteById(Long id);
}
