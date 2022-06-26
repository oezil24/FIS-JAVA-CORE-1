package fis.com.sprint4.dao.mem;


import fis.com.sprint4.dao.DAO;
import fis.com.sprint4.entity.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StorageDAO implements DAO<Storage> {
    private final List<Storage> storages = new ArrayList<>();

    @Override
    public List<Storage> getAll() {
        return storages;
    }

    @Override
    public Optional<Storage> get(Long id) {
        return storages.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    @Override
    public void save(Storage storage) {
        storages.add(storage);
    }

    @Override
    public void update(Storage storage) {
        get(storage.getId()).ifPresent(e -> {
            e.setName(e.getName());
            e.setLocation(e.getLocation());
        });
    }

    @Override
    public void delete(Storage storage) {
        get(storage.getId()).ifPresent(storages::remove);
    }

    @Override
    public void deleteId(Long id) {

    }
}
