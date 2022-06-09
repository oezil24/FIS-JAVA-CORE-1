package com.dao.impl;

import com.dao.ICriminalCaseDAO;
import com.model.CriminalCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CriminalCaseDAOTest {

    @Test
    void insert() {
        CriminalCase criminalCase = new CriminalCase();
        // init data
        // ...
        //A2: Action
        int before = MemoryDataSource.CRIMINAL_CASES.size();
        ICriminalCaseDAO iCriminalCaseDAO = new CriminalCaseDAO();
        iCriminalCaseDAO.insert(criminalCase);
        //A3: Assert
        assertEquals(before+1, MemoryDataSource.CRIMINAL_CASES.size());
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
        ICriminalCaseDAO criminalCaseDAO = new CriminalCaseDAO();
        List<CriminalCase> criminalCaseList = criminalCaseDAO.findAll();
        System.out.println(criminalCaseList);
    }

    @Test
    void deleteById() {
    }
}