package com.dao.jdbc;

import com.dao.IDetectiveDAO;
import com.dao.jdbc.mapper.DataMapper;
import com.model.Detective;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCDetectiveDAO implements IDetectiveDAO {
    private final static Logger logger = LoggerFactory.getLogger(JDBCDetectiveDAO.class);
    @Override
    public Optional<Detective> get(long id) {
        return Optional.empty();
    }

    @Override
    public void insert(Detective detective) {

    }

    @Override
    public void update(Detective detective) {

    }

    @Override
    public List<Detective> findAll() {
        List<Detective> detectives = new ArrayList<>();
        try (Connection con = SPConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM detective");
             ResultSet rs = stmt.executeQuery ()) {

            while (rs.next()) {
                Detective detective = DataMapper.getDetective(rs);
                if(detective != null) detectives.add(detective);
            } // end of while
        } catch (Exception e) {
            logger.error(e.toString());
        } // end of try-with-resources
        return detectives;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
