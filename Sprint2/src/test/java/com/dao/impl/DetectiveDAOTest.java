package com.dao.impl;

import com.dao.IDetectiveDAO;
import com.dao.jdbc.JDBCDetectiveDAO;
import com.model.Detective;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DetectiveDAOTest {

    @Test
    void insert() {
        Detective detective = new Detective();
        // init data
        // ...
        //A2: Action
        int before = MemoryDataSource.DETECTIVES.size();
        IDetectiveDAO iDetectiveDAO = new JDBCDetectiveDAO();
        iDetectiveDAO.insert(detective);
        //A3: Assert
        assertEquals(before, MemoryDataSource.DETECTIVES.size());
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }
}