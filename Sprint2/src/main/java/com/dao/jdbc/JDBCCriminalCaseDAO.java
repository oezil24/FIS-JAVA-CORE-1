package com.dao.jdbc;

import com.dao.ICriminalCaseDAO;
import com.dao.IDAO;
import com.dao.impl.CriminalCaseDAO;
import com.dao.jdbc.mapper.CriminalCaseMapper;
import com.model.CriminalCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCCriminalCaseDAO implements ICriminalCaseDAO {
    Connection cnn;
    private final static Logger logger = LoggerFactory.getLogger(JDBCCriminalCaseDAO.class);

    @Override
    public Optional<CriminalCase> get(long id) {
        return Optional.empty();
    }

    @Override
    public void insert(CriminalCase criminalCase) {

    }

    @Override
    public void update(CriminalCase criminalCase) {

    }

    @Override
    public List<CriminalCase> findAll() {
        List<CriminalCase> criminalCases = new ArrayList<>();
        try (
             PreparedStatement stmt = cnn.prepareStatement("SELECT * FROM criminal_case");
             ResultSet rs = stmt.executeQuery ()) {

            while (rs.next()) {
                CriminalCase criminalCase = CriminalCaseMapper.get(rs);
                if(criminalCase != null) criminalCases.add(criminalCase);
            } // end of while
        } catch (SQLException e) {
            logger.error(e.toString());
        } // end of try-with-resources
        return criminalCases;

    }

    @Override
    public void deleteById(Long id) {

    }
}
