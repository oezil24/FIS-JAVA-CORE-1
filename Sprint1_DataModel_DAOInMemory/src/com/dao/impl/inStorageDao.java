package com.dao.impl;

import com.dao.inMemoryDAO;
import com.model.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class inStorageDao implements inMemoryDAO<Storage> {
    private Map<Long, Storage> storageMap = new HashMap<>();

    public Map<Long, Storage> getStorageMap() {
        return storageMap;
    }

    public void setStorageMap(Map<Long, Storage> storageMap) {
        this.storageMap = storageMap;
    }

    @Override
    public Storage insertOrUpdate(Storage storage) {
        storageMap.put(storage.getId(), storage);
        return storage;
    }

    @Override
    public List<Storage> findAll() {
        return new ArrayList<>(storageMap.values());
    }

    @Override
    public void deleteById(Long id) {
        storageMap.remove(id);
    }
}
