package fis.com.sprint4.dao.jdbc;


import fis.com.sprint4.entity.CriminalCase;
import fis.com.sprint4.entity.Evidence;
import fis.com.sprint4.entity.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JdbcEvidenceTest {

    JdbcEvidence jdbcEvidence = new JdbcEvidence();
    @Test
    void insert() {
        Evidence evidence = new Evidence();
        evidence.setVersion(1);
        evidence.setArchived(true);
        evidence.setItemName("pákas");
        evidence.setNotes("12121qwdasd");
        evidence.setNumber("19102129");
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setId(3L);
        evidence.setCriminalCase(criminalCase);
        Storage storage = new Storage();
        storage.setId(1L);
        evidence.setStorage(storage);
        jdbcEvidence.insert(evidence);
        System.out.println("Done");
    }

    @Test
    void update() {
        Evidence evidence = new Evidence();
        evidence.setVersion(1);
        evidence.setArchived(true);
        evidence.setItemName("Update");
        evidence.setNotes("Note Update");
        evidence.setNumber("12391919");
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setId(3L);
        evidence.setCriminalCase(criminalCase);
        Storage storage = new Storage();
        storage.setId(1L);
        evidence.setStorage(storage);
        evidence.setId(1L);
        jdbcEvidence.update(evidence);
        System.out.println("Done");
    }

    @Test
    void delete() {
        jdbcEvidence.delete(1L);
    }

    @Test
    void selectAll() {
        System.out.println(jdbcEvidence.selectAll());
    }

    @Test
    void selectById() {
        System.out.println(jdbcEvidence.selectById(1L));
    }

}