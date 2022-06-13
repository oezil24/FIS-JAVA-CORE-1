package com.dao.jdbc;

import com.dao.IDetectiveDAO;
import com.dao.jdbc.mapper.DataMapper;
import com.dao.jdbc.mapper.DatabaseUpdate;
import com.dao.jdbc.mapper.DatabaseWriter;
import com.model.Detective;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCDetectiveDAO implements IDetectiveDAO {
    private final static Logger logger = LoggerFactory.getLogger(JDBCDetectiveDAO.class);
    @Override
    public Optional<Detective> get(long id) {
        return findAll().stream().filter(detective -> detective.getId() == id).findFirst();
    }

    @Override
    public void insert(Detective detective) {
        Connection con;
        PreparedStatement stmt;
        try {
            con = SPConnection.getConnection();
            stmt = con.prepareStatement("INSERT INTO Detective VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            DatabaseWriter.setDetective(detective, stmt);
            if (stmt.executeUpdate() > 0) {
                logger.info("Added a new detective successfully");
            } else {
                logger.info("Fail to add a new detective");
            }
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

    @Override
    public boolean update(Detective detective) {
        boolean isUpdated = false;
        if (findById(detective.getId()) == false) {
            logger.info("No detective with this id = "+detective.getId()+"to update");
        }
        else {
            try {
                Connection con = SPConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement("UPDATE Detective SET version = ?, createdAt = ? ,modifiedAt = ?, username = ?, firstName = ?, lastName = ?, pw = ? , hiringDate = ?, badgeNumber = ?, rankOfDetective = ?, armed = ?, stt = ? WHERE detectiveId = ?" );
                DatabaseUpdate.updateDetective(detective, stmt);
                stmt.setLong(13, detective.getId());
                if (stmt.executeUpdate() > 0) {
                    logger.info("Updated");
                    isUpdated = true;
                }
                else {
                    logger.error("Can not update");
                }
            } catch (Exception e) {
                logger.error(e.toString());
            }

        }
        return isUpdated;
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
    public boolean delete(Detective detective) {
        boolean canDelete = false;
        if (!findById(detective.getId())) {
            logger.info("No detective with id = "+detective.getId());
            return false;
        }
        try {
            Connection con = SPConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Detective WHERE detectiveId = ?");
            stmt.setLong(1, detective.getId());
            if (stmt.executeUpdate() > 0) {
                logger.info("Successfully deleted detective with id = "+ detective.getId());
                canDelete = true;
            }
            else {
                logger.error("Failed to delete detective with id = "+ detective.getId());
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
            logger.info("No detective with the id = "+id);
        }
        else {
            try {
                Connection con = SPConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement("DELETE FROM Detective WHERE detectiveId = ?");
                stmt.setLong(1,id);
                if (stmt.executeUpdate() > 0) {
                    logger.info("Successfully deleted ");
                    canDelete = true;
                }
                else {
                    logger.error("Failed to delete detective with id = "+ id);
                }
            } catch (Exception ex) {
                logger.error(ex.toString());
            }
        }
        return canDelete;
    }

    @Override
    public boolean findById(long id) {
        Detective detective;
        boolean canFind = false;
        try (Connection con = SPConnection.getConnection()){
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Detective WHERE detectiveId = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                detective = DataMapper.getDetective(rs);
                if (detective == null) {
                    logger.info("Can not found");
                }
                else {
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
            canDelete = st.executeUpdate("DELETE FROM Detective");
        } catch(Exception ex) {
            logger.error(ex.toString());
        }
        return canDelete > 0;
    }
}
