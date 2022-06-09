package com.dao;

import java.util.List;

public interface IMemoryDAO<T> {
    T insert(T t);
    T update(T t);
    List<T>findAll();
    void deleteById(Long id);
}
