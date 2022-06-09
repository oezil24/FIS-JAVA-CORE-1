package com.dao.impl;

import com.dao.ICriminalCaseDAO;
import com.dao.IDAO;
import com.model.CriminalCase;
import com.model.Detective;

import java.util.*;

public class CriminalCaseDAO implements ICriminalCaseDAO {


    @Override
    public Optional<CriminalCase> get(long id) {
        return MemoryDataSource.CRIMINAL_CASES.stream()
                .filter(criminalCase -> criminalCase.getId()==id)
                .findFirst();
    }

    @Override
    public void insert(CriminalCase criminalCase) {
        MemoryDataSource.CRIMINAL_CASES.add(criminalCase);
    }

    @Override
    public void update(CriminalCase criminalCase) {
        Optional<CriminalCase> criminalCaseOptional = get(criminalCase.getId());
        if(criminalCaseOptional.isPresent()){
            CriminalCase updatedCriminalCase = criminalCaseOptional.get();
            updatedCriminalCase.replaceWith(criminalCase);
        }
    }

    @Override
    public List<CriminalCase> findAll() {
        return MemoryDataSource.CRIMINAL_CASES;
    }

    @Override
    public void deleteById(Long id) {
        for(CriminalCase criminalCase : MemoryDataSource.CRIMINAL_CASES) {
            if (criminalCase.getId() == id){
                MemoryDataSource.CRIMINAL_CASES.remove(criminalCase);
                return;
            }
        }

    }
}
