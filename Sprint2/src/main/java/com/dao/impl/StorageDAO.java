package com.dao.impl;

import com.dao.IDAO;
import com.dao.IStorageDAO;
import com.model.Storage;
import com.model.TrackEntry;

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
    public void update(Storage storage) {
        Optional<Storage> storageOptional = get(storage.getId());
        if(storageOptional.isPresent()){
            Storage updatedStorage = storageOptional.get();
            updatedStorage.replaceWith(storage);
        }

    }

    @Override
    public List<Storage> findAll() {
        return MemoryDataSource.STORAGES;
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
