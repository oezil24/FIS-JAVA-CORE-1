package com.dao.impl;

import com.dao.IDAO;
import com.model.AbstractEntity;
import com.model.Evidence;

import java.util.*;

public class AbstractEntityDAO implements IDAO<AbstractEntity> {

    @Override
    public Optional<AbstractEntity> get(long id) {
        return MemoryDataSource.ABSTRACT_ENTITIES.stream().filter(abstractEntity -> abstractEntity.getId()==id).findFirst();
    }

    @Override
    public void insert(AbstractEntity abstractEntity) {
        if(!MemoryDataSource.ABSTRACT_ENTITIES.stream().filter(
                item -> item.getId()==abstractEntity.getId()).findFirst().isPresent()
        )
            MemoryDataSource.ABSTRACT_ENTITIES.add(abstractEntity);

    }

    @Override
    public boolean update(AbstractEntity abstractEntity) {
        boolean isUpdated = false;
        Optional<AbstractEntity> abstractEntityOptional = get(abstractEntity.getId());
        if(abstractEntityOptional.isPresent()){
            AbstractEntity updatedAbstractEntity = abstractEntityOptional.get();
            updatedAbstractEntity.replaceWith(abstractEntity);
        }
        return isUpdated;
    }

    @Override
    public List<AbstractEntity> findAll() {
        return MemoryDataSource.ABSTRACT_ENTITIES;
    }

    @Override
    public boolean delete(AbstractEntity abstractEntity) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        for(AbstractEntity abstractEntity : MemoryDataSource.ABSTRACT_ENTITIES) {
            if (abstractEntity.getId() == id){
                MemoryDataSource.ABSTRACT_ENTITIES.remove(abstractEntity);
                return true;
            }
        }
    return false;
    }
}
