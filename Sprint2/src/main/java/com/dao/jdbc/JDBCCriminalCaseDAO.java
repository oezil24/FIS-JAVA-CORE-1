package com.dao.jdbc;

import com.dao.ICriminalCaseDAO;
import com.dao.IDAO;
import com.dao.impl.CriminalCaseDAO;
import com.dao.jdbc.mapper.DataMapper;
import com.dao.jdbc.mapper.DataMapper;
import com.model.CriminalCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCCriminalCaseDAO implements ICriminalCaseDAO {
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
        try (Connection con = SPConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM criminal_case");
             ResultSet rs = stmt.executeQuery ()) {

            while (rs.next()) {
                CriminalCase criminalCase = DataMapper.getCriminalCase(rs);
                logger.debug(criminalCase.toString());

                if(criminalCase != null) criminalCases.add(criminalCase);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        // end of try-with-resources
        return criminalCases;
    }

    @Override
    public boolean deleteById(Long id) {
        return true;
    }
}
