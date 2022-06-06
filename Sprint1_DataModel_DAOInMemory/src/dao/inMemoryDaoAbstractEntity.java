package dao;

import model.AbstractEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class inMemoryDaoAbstractEntity implements inMemoryDAO<AbstractEntity> {
    private Map<Long, AbstractEntity> abstractEntityMap = new HashMap<>();

    public Map<Long, AbstractEntity> getAbstractEntityMap() {
        return abstractEntityMap;
    }

    public void setAbstractEntityMap(Map<Long, AbstractEntity> abstractEntityMap) {
        this.abstractEntityMap = abstractEntityMap;
    }

    @Override
    public AbstractEntity insertOrUpdate(AbstractEntity abstractEntity) {
        abstractEntityMap.put(abstractEntity.getId(), abstractEntity);
        return abstractEntity;
    }

    @Override
    public List<AbstractEntity> findAll() {
        if (abstractEntityMap.isEmpty()) {
            throw new NullPointerException("Abstract Entity is null");
        } else {
            return new ArrayList<>(abstractEntityMap.values());
        }
    }

    @Override
    public void deleteById(Long id) {
        abstractEntityMap.remove(id);
    }
}
