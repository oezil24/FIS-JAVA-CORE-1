package com.dao.jdbc;

import com.dao.ICriminalCaseDAO;
import com.dao.jdbc.mapper.DataMapper;
import com.dao.jdbc.mapper.DatabaseUpdate;
import com.dao.jdbc.mapper.DatabaseWriter;
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
        return findAll().stream().filter(criminalCase -> criminalCase.getId() == id).findFirst();
    }

    @Override
    public void insert(CriminalCase criminalCase) {
        Connection con;
        PreparedStatement stmt;
        try {
            con = SPConnection.getConnection();
            stmt = con.prepareStatement("INSERT INTO CriminalCase VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            DatabaseWriter.setCriminalCase(criminalCase, stmt);
            if (stmt.executeUpdate() > 0) {
                logger.info("Added a new criminalCase successfully");
            } else {
                logger.info("Fail to add a new criminalCase");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }

    }

    @Override
    public boolean update(CriminalCase criminalCase) {
        boolean isUpdated = false;
        if (findById(criminalCase.getId()) == false) {
            logger.info("No criminalCase with this id = " + criminalCase.getId() + "to update");
        } else {
            try {
                Connection con = SPConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement("UPDATE CriminalCase SET version = ?, createdAt = ? ,modifiedAt = ?, number = ?,type = ?, shortDescription = ?, detailedDescription = ?,status = ?,note =? WHERE criminalCaseId = ?");
                DatabaseUpdate.updateCriminalCase(criminalCase, stmt);
                stmt.setLong(10, criminalCase.getId());
                if (stmt.executeUpdate() > 0) {
                    logger.info("Updated");
                    isUpdated = true;
                } else {
                    logger.error("Can not update");
                }
            } catch (Exception e) {
                logger.error(e.toString());
            }

        }
        return isUpdated;
    }

    @Override
    public List<CriminalCase> findAll() {
        List<CriminalCase> criminalCases = new ArrayList<>();
        try (Connection con = SPConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM CriminalCase");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CriminalCase criminalCase = DataMapper.getCriminalCase(rs);
                logger.debug(criminalCase.toString());

                if (criminalCase != null) criminalCases.add(criminalCase);
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        // end of try-with-resources
        return criminalCases;
    }

    @Override
    public boolean delete(CriminalCase criminalCase) {
        boolean canDelete = false;
        if (!findById(criminalCase.getId())) {
            logger.info("No criminalCase with id = " + criminalCase.getId());
            return false;
        }
        try {
            Connection con = SPConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM CriminalCase WHERE criminalCaseId = ?");
            stmt.setLong(1, criminalCase.getId());
            if (stmt.executeUpdate() > 0) {
                logger.info("Successfully deleted criminalCase with id = " + criminalCase.getId());
                canDelete = true;
            } else {
                logger.error("Failed to delete criminalCase with id = " + criminalCase.getId());
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return canDelete;
    }

    @Override
    public boolean deleteById(Long id) {
        boolean canDelete = false;
        if (!findById(id)) {
            logger.info("No criminalCase with the id = " + id);
        } else {
            try {
                Connection con = SPConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM CriminalCase WHERE criminalCaseId = ?");
                stmt.setLong(1, id);
                if (stmt.executeUpdate() > 0) {
                    logger.info("Successfully deleted ");
                    canDelete = true;
                } else {
                    logger.error("Failed to delete criminalCase with id = " + id);
                }
            } catch (Exception ex) {
                logger.error(ex.toString());
            }
        }
        return canDelete;
    }

    @Override
    public boolean findById(long id) {

        CriminalCase criminalCase;
        boolean canFind = false;
        try (Connection con = SPConnection.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM CriminalCase WHERE criminalCaseId = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                criminalCase = DataMapper.getCriminalCase(rs);
                if (criminalCase == null) {
                    logger.info("Can not found");
                } else {
                    canFind = true;
                }
            }
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return canFind;
    }

    @Override
    public boolean deleteAll() {
        int canDelete = 0;
        try {
            Connection con = SPConnection.getConnection();
            Statement st = con.createStatement();
            canDelete = st.executeUpdate("DELETE FROM CriminalCase");
        } catch (Exception ex) {
            logger.error(ex.toString());
        }
        return canDelete > 0;
    }
}

