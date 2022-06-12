package com.dao.impl;

import com.dao.IStorageDAO;
import com.model.Storage;

import java.util.*;

public class StorageDAO implements IStorageDAO {
    @Override
    public Optional<Storage> get(long id) {
        return MemoryDataSource.STORAGES.stream().filter(storage -> storage.getId()==id).findFirst();
    }

    @Override
    public void insert(Storage storage) {
        if (!MemoryDataSource.STORAGES.stream().filter(
                item -> item.getId()==storage.getId()).findFirst().isPresent()
        )
        MemoryDataSource.STORAGES.add(storage);

    }

    @Override
    public boolean update(Storage storage) {
        boolean isUpdated = false;
        Optional<Storage> storageOptional = get(storage.getId());
        if(storageOptional.isPresent()){
            Storage updatedStorage = storageOptional.get();
            updatedStorage.replaceWith(storage);
        }
        return isUpdated;

    }

    @Override
    public List<Storage> findAll() {
        return MemoryDataSource.STORAGES;
    }

    @Override
    public boolean delete(Storage storage) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        for(Storage storage : MemoryDataSource.STORAGES) {
            if (storage.getId() == id){
                MemoryDataSource.STORAGES.remove(storage);
                return true;
            }
        }
        return false;
    }
}
