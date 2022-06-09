package com.dao.impl;

import com.dao.IDAO;
import com.model.AbstractEntity;
import com.model.Detective;

import java.util.*;

public class AbstractEntityDAO implements IDAO<AbstractEntity> {

    @Override
    public Optional<AbstractEntity> get(long id) {
        return MemoryDataSource.ABSTRACT_ENTITIES.stream().filter(abstractEntity -> abstractEntity.getId()==id).findFirst();
    }

    @Override
    public void insert(AbstractEntity abstractEntity) {
        MemoryDataSource.ABSTRACT_ENTITIES.add(abstractEntity);

    }

    @Override
    public void update(AbstractEntity abstractEntity) {
        Optional<AbstractEntity> abstractEntityOptional = get(abstractEntity.getId());
        if(abstractEntityOptional.isPresent()){
            AbstractEntity updatedAbstractEntity = abstractEntityOptional.get();
            updatedAbstractEntity.replaceWith(abstractEntity);
        }
    }

    @Override
    public List<AbstractEntity> findAll() {
        return MemoryDataSource.ABSTRACT_ENTITIES;
    }

    @Override
    public void deleteById(Long id) {
        for(AbstractEntity abstractEntity : MemoryDataSource.ABSTRACT_ENTITIES) {
            if (abstractEntity.getId() == id){
                MemoryDataSource.ABSTRACT_ENTITIES.remove(abstractEntity);
                return;
            }
        }

    }
}
