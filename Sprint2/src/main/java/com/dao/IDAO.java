package com.dao;

import java.util.List;
import java.util.Optional;

public interface IDAO<T> {
    Optional<T> get(long id);
    void insert(T t);
    void update(T t);
    List<T>findAll();
    void deleteById(Long id);
}
