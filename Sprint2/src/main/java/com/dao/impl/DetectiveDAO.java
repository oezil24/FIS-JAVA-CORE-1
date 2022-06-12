package com.dao.impl;

import com.dao.IDAO;
import com.model.CriminalCase;
import com.model.Detective;

import java.util.*;

public class DetectiveDAO implements IDAO<Detective> {
    @Override
    public Optional<Detective> get(long id) {
            return MemoryDataSource.DETECTIVES.stream()
                    .filter(criminalCase -> criminalCase.getId()==id)
                    .findFirst();
    }

    @Override
    public void insert(Detective detective) {
        if(!MemoryDataSource.DETECTIVES.stream().filter(
                item -> item.getId()==detective.getId()).findFirst().isPresent()
        )
                    MemoryDataSource.DETECTIVES.add(detective);
    }

    @Override
    public boolean update(Detective detective) {
        boolean isUpdated = false;
        Optional<Detective> detectiveCaseOptional = get(detective.getId());
        if(detectiveCaseOptional.isPresent()){
            Detective updatedDetective = detectiveCaseOptional.get();
            updatedDetective.replaceWith(detective);
        }
        return isUpdated;
    }

    @Override
    public List<Detective> findAll() {
        return MemoryDataSource.DETECTIVES;
    }

    @Override
    public boolean delete(Detective detective) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        for(Detective detective : MemoryDataSource.DETECTIVES) {
            if (detective.getId() == id){
                MemoryDataSource.CRIMINAL_CASES.remove(detective);
                return true;
            }
        }
        return false;
    }
}

