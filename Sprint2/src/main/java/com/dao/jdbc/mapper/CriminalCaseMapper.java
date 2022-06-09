package com.dao.jdbc.mapper;

import com.model.CriminalCase;
import com.model.Detective;
import com.model.Evidence;
import com.model.enumerable.CaseStatus;
import com.model.enumerable.CaseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class CriminalCaseMapper {
    public static final Logger logger = LoggerFactory.getLogger(CriminalCaseMapper.class);

    public static CriminalCase get(ResultSet criminalCaseResultSet){
        try {
            CriminalCase criminalCase = new CriminalCase();
            criminalCase.setId(criminalCaseResultSet.getLong("id"));
            criminalCase.setNumber(criminalCaseResultSet.getString("number"));
            criminalCase.setType(CaseType.valueOf(criminalCaseResultSet.getString("type")));
            criminalCase.setShortDescription(criminalCaseResultSet.getString("shortDescription"));
            criminalCase.setDetailedDescription(criminalCaseResultSet.getString("detailedDescription"));
            criminalCase.setStatus(CaseStatus.valueOf(criminalCaseResultSet.getString("status")));
            criminalCase.setNotes(criminalCaseResultSet.getString("note"));
            criminalCase.setEvidenceSet((Set<Evidence>) criminalCaseResultSet.getObject("evidenceSet"));
            criminalCase.setLeadInvestigator((Detective) criminalCaseResultSet.getObject("leadInvestigator"));
            criminalCase.setAssigned((Set<Detective>) criminalCaseResultSet.getObject("assigned"));

            return criminalCase;
        }catch (SQLException ex){
            logger.error(ex.toString());
        }
        return null;
    }
}
