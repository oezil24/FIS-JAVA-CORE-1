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
        MemoryDataSource.EVIDENCES.add(evidence);
    }

    @Override
    public void update(Evidence evidence) {
        Optional<Evidence> evidenceOptional = get(evidence.getId());
        if(evidenceOptional.isPresent()){
            Evidence updatedEvidence = evidenceOptional.get();
            updatedEvidence.replaceWith(evidence);
        }

    }

    @Override
    public List<Evidence> findAll() {
        return MemoryDataSource.EVIDENCES;
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
