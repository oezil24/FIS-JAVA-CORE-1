package com.dao.impl;

import com.dao.IDAO;
import com.dao.IStorageDAO;
import com.model.Storage;

import java.util.*;

public class StorageDAO implements IStorageDAO {
    @Override
    public Optional<Storage> get(long id) {
        return Optional.empty();
    }

    @Override
    public void insert(Storage storage) {

    }

    @Override
    public void update(Storage storage) {

    }

    @Override
    public List<Storage> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
