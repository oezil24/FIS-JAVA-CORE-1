package com.dao.impl;

import com.dao.ICriminalCaseDAO;
import com.model.CriminalCase;
import com.model.Evidence;

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
        if(!MemoryDataSource.CRIMINAL_CASES.stream().filter(
                item -> item.getId()==criminalCase.getId()).findFirst().isPresent()
        )
            MemoryDataSource.CRIMINAL_CASES.add(criminalCase);
    }

    @Override
    public boolean update(CriminalCase criminalCase) {
        boolean isUpdated = false;
        Optional<CriminalCase> criminalCaseOptional = get(criminalCase.getId());
        if(criminalCaseOptional.isPresent()){
            CriminalCase updatedCriminalCase = criminalCaseOptional.get();
            updatedCriminalCase.replaceWith(criminalCase);
        }
        return isUpdated;
    }

    @Override
    public List<CriminalCase> findAll() {
        return MemoryDataSource.CRIMINAL_CASES;
    }

    @Override
    public boolean delete(CriminalCase criminalCase) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        for(CriminalCase criminalCase : MemoryDataSource.CRIMINAL_CASES) {
            if (criminalCase.getId() == id){
                MemoryDataSource.CRIMINAL_CASES.remove(criminalCase);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean findById(long id) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
