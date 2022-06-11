package com.dao.jdbc.mapper;

import com.model.*;
import com.model.enumerable.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Set;

public class DataMapper {
    static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");

    static DateTimeFormatter dtfb = new DateTimeFormatterBuilder()
            .parseCaseSensitive()
            .appendPattern("dd/MM/yyyy HH:mm:ss.SS")
            .toFormatter();
    public static final Logger logger = LoggerFactory.getLogger(DataMapper.class);

    public static CriminalCase getCriminalCase(ResultSet criminalCaseResultSet) {
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
        } catch (SQLException ex) {
            logger.error(ex.toString());
        }
        return null;
    }

    public static Detective getDetective(ResultSet detectiveResultSet) {
        try {
            Detective detective = new Detective();

            detective.setId(detectiveResultSet.getLong("id"));
            detective.setVersion(detectiveResultSet.getInt("version"));
            detective.setCreatedAt(new java.sql.Timestamp(detectiveResultSet.getDate("createdAt").getTime()).toLocalDateTime());
            detective.setModifiedAt(new java.sql.Timestamp(detectiveResultSet.getDate("modifiedAt").getTime()).toLocalDateTime());
            detective.setUsername(detectiveResultSet.getString("username"));
            detective.setFirstName(detectiveResultSet.getString("firstName"));
            detective.setLastName(detectiveResultSet.getString("lastName"));
            detective.setPassword(detectiveResultSet.getString("pw"));
            detective.setHiringDate(new java.sql.Timestamp(detectiveResultSet.getDate("hiringDate").getTime()).toLocalDateTime());
            detective.setBadgeNumber(detectiveResultSet.getString("badgeNumber"));
            detective.setRank(Rank.valueOf(detectiveResultSet.getString("rank")));
            detective.setArmed(detectiveResultSet.getInt("armed") == 1);
            detective.setStatus(EmploymentStatus.valueOf(detectiveResultSet.getString("status")));

            return detective;
        } catch (SQLException ex) {
            logger.error(ex.toString());
        }
        return null;
    }

    public static Storage getStorage(ResultSet storageResultSet) {
        try {
            Storage storage = new Storage();

            storage.setId(storageResultSet.getLong("id"));
            storage.setVersion(storageResultSet.getInt("version"));
            storage.setCreatedAt(new java.sql.Timestamp(storageResultSet.getDate("createdAt").getTime()).toLocalDateTime());
            storage.setModifiedAt(new java.sql.Timestamp(storageResultSet.getDate("modifiedAt").getTime()).toLocalDateTime());
            storage.setName(storageResultSet.getString("name"));
            storage.setLocation(storageResultSet.getString("location"));

            return storage;
        } catch (SQLException ex) {
            logger.error(ex.toString());
        }
        return null;
    }
    public static TrackEntry getTrackEntry(ResultSet trackEntryResultSet){
        try {
            TrackEntry trackEntry = new TrackEntry();

            trackEntry.setId(trackEntryResultSet.getLong("id"));
            trackEntry.setVersion(trackEntryResultSet.getInt("version"));
            trackEntry.setCreatedAt(new java.sql.Timestamp(trackEntryResultSet.getDate("createdAt").getTime()).toLocalDateTime());
            trackEntry.setModifiedAt(new java.sql.Timestamp(trackEntryResultSet.getDate("modifiedAt").getTime()).toLocalDateTime());
            trackEntry.setDate(new java.sql.Timestamp(trackEntryResultSet.getDate("date").getTime()).toLocalDateTime());
            trackEntry.setAction(TrackAction.valueOf(trackEntryResultSet.getString("action")));
            trackEntry.setReason(trackEntryResultSet.getString("reason"));

            return trackEntry;
        } catch (SQLException ex) {
            logger.error(ex.toString());
        }
        return null;
    }
    public static Evidence getEvidence(ResultSet evidenceResultSet){
        try {
            Evidence evidence = new Evidence();
            evidence.setCriminalCase((CriminalCase) evidenceResultSet.getObject("criminalCase"));
            evidence.setStorage((Storage) evidenceResultSet.getObject("storage"));
            evidence.setNumber(evidenceResultSet.getString("number"));
            evidence.setItemName(evidenceResultSet.getString("itemName"));
            evidence.setNotes(evidenceResultSet.getString("notes"));
            evidence.setArchived(evidenceResultSet.getBoolean("archived"));
            evidence.setTrackEntrySet((Set<TrackEntry>) evidenceResultSet.getObject("trackEntrySet"));
            return evidence;
        } catch (SQLException ex) {
            logger.error(ex.toString());
        }

        return null;
    }
}
