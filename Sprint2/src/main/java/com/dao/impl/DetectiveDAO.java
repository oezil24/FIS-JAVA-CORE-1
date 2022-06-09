package com.dao.impl;

import com.dao.IDAO;
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
        MemoryDataSource.DETECTIVES.add(detective);
    }

    @Override
    public void update(Detective detective) {
        Optional<Detective> detectiveOptional = get(detective.getId());
        if(detectiveOptional.isPresent()){
            Detective updatedCriminalCase = detectiveOptional.get();
            updatedCriminalCase.replaceWith(detective);
        }
    }

    @Override
    public List<Detective> findAll() {
        return MemoryDataSource.DETECTIVES;
    }

    @Override
    public void deleteById(Long id) {
        for(Detective detective : MemoryDataSource.DETECTIVES) {
            if (detective.getId() == id){
                MemoryDataSource.CRIMINAL_CASES.remove(detective);
                return;
            }
        }
    }
}

