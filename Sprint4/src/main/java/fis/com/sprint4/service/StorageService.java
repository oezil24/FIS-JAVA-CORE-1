package fis.com.sprint4.service;

import fis.com.sprint4.entity.Storage;

import java.util.Set;

public interface StorageService {
    Storage addStorage(Storage storage);

    Storage updateStorage(Storage storage);

    Set<Storage> getStorages();

    Storage getStorage(Long id);

    void deleteStorage(Long id);
}
