package com.dao.impl;

import com.dao.IDAO;
import com.model.Detective;
import com.model.Evidence;

import java.util.*;

public class EvidenceDAO implements IDAO<Evidence> {

    @Override
    public Optional<Evidence> get(long id) {
        return MemoryDataSource.EVIDENCES.stream()
                .filter(evidence -> evidence.getId()==id)
                .findFirst();
    }

    @Override
    public void insert(Evidence evidence) {
        if(!MemoryDataSource.EVIDENCES.stream().filter(
                item -> item.getId()==evidence.getId()).findFirst().isPresent()
        )
        MemoryDataSource.EVIDENCES.add(evidence);
    }

    @Override
    public boolean update(Evidence evidence) {
        boolean isUpdated = false;
        Optional<Evidence> evidenceOptional = get(evidence.getId());
        if(evidenceOptional.isPresent()){
            Evidence updatedEvidence = evidenceOptional.get();
            updatedEvidence.replaceWith(evidence);
        }
        return isUpdated;
    }

    @Override
    public List<Evidence> findAll() {
        return MemoryDataSource.EVIDENCES;
    }

    @Override
    public boolean delete(Evidence evidence) {
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
