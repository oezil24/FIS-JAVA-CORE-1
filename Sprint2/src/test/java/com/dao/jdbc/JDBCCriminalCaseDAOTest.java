package com.dao.jdbc;

import com.dao.ICriminalCaseDAO;
import com.model.CriminalCase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JDBCCriminalCaseDAOTest {

    @Test
    void get() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
//A1
        ICriminalCaseDAO criminalCaseDAO = new JDBCCriminalCaseDAO();

        //A2
        List<CriminalCase> criminalCaseList = criminalCaseDAO.findAll();

        //A3: Assert
        System.out.println(criminalCaseList);
    }
    @Test
    void deleteById() {
    }
}