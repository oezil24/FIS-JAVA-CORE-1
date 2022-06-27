package fis.com.sprint4.dao.jdbc_spring.impl;


import fis.com.sprint4.dao.DAO;
import fis.com.sprint4.entity.CriminalCase;
import fis.com.sprint4.entity.Detective;
import fis.com.sprint4.util.CaseStatus;
import fis.com.sprint4.util.CaseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class CriminalCaseImpl implements DAO<CriminalCase> {

    private static final Logger log = LoggerFactory.getLogger(CriminalCaseImpl.class);
    private final JdbcTemplate jdbcTemplate;

    public CriminalCaseImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    RowMapper<CriminalCase> rowMapper = (rs, rowNum) -> {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setId(rs.getLong(1));
        criminalCase.setModifiedAt(rs.getDate(3).toLocalDate().atStartOfDay());
        criminalCase.setCreateAt(rs.getDate(2).toLocalDate().atStartOfDay());
        criminalCase.setVersion(rs.getInt(4));
        criminalCase.setDetailedDescription(rs.getString(5));
        criminalCase.setNotes(rs.getString(6));
        criminalCase.setNumber(rs.getString(7));
        criminalCase.setShortDescription(rs.getString(8));
        criminalCase.setStatus(CaseStatus.valueOf(rs.getString(9)));
        criminalCase.setType(CaseType.valueOf(rs.getString(10)));
        Detective d = new Detective();
        d.setId(rs.getLong(11));
        criminalCase.setLeadInvestigator(d);
        return criminalCase;
    };

    @Override
    public List<CriminalCase> getAll() {
        String sql = "SELECT * FROM criminal_case";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Optional<CriminalCase> get(Long id) {
        String sql = "select * from criminal_case where id = ?";
        CriminalCase criminalCase = null;
        try {
            criminalCase = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        } catch (DataAccessException ex) {
            log.info("not found: " + id);
        }
        return Optional.ofNullable(criminalCase);
    }

    @Override
    public void save(CriminalCase criminalCase) {
        String sql = "insert into criminal_case\n" +
                "(create_at, modified_at, version, detailed_description, notes, number, short_description, status, case_type, lead_investigator) VALUES \n" +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int insert = jdbcTemplate.update(sql, LocalDateTime.now(), LocalDateTime.now(), criminalCase.getVersion(), criminalCase.getDetailedDescription(), criminalCase.getNotes(), criminalCase.getNumber(), criminalCase.getShortDescription(), criminalCase.getStatus() + "", criminalCase.getType() + "", criminalCase.getLeadInvestigator().getId());
        if (insert == 1) {
            log.info("New course created: " + criminalCase.getId());
        }
    }

    @Override
    public void update(CriminalCase criminalCase) {
        String sql = "update criminal_case\n" +
                "set modified_at = ?, version = ?, detailed_description = ?, notes = ?, number = ?\n" +
                ", short_description = ?, status = ?, case_type = ?, lead_investigator = ?\n" +
                "where  id = ?";
        int update = jdbcTemplate.update(sql, LocalDateTime.now(), criminalCase.getVersion(), criminalCase.getDetailedDescription(),
                criminalCase.getNotes(), criminalCase.getNumber(), criminalCase.getShortDescription(), criminalCase.getStatus() + "",
                criminalCase.getType() + "", criminalCase.getLeadInvestigator().getId(), criminalCase.getId());
        if(update == 1){
            log.info("udate: " + criminalCase.getId());
        }
    }

    @Override
    public void delete(CriminalCase criminalCase) {

    }

    @Override
    public void deleteId(Long id) {
        jdbcTemplate.update("delete from criminal_case where  id = ?", id);
    }
}
