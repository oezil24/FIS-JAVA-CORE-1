package com.dao;

import java.util.List;
import java.util.Optional;

public interface IDAO<T> {
    Optional<T> get(long id);
    void insert(T t);
    boolean update(T t);
    List<T>findAll();
    boolean delete (T t);
    boolean deleteById(Long id);
}