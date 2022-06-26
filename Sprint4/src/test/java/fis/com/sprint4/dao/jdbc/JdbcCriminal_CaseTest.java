package fis.com.sprint4.dao.jdbc;


import fis.com.sprint4.entity.CriminalCase;
import fis.com.sprint4.entity.Detective;
import fis.com.sprint4.util.CaseStatus;
import fis.com.sprint4.util.CaseType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class JdbcCriminal_CaseTest {

    JdbcCriminal_Case jdbcCriminal_case = new JdbcCriminal_Case();

    @Test
    void insert() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setVersion(1);
        criminalCase.setDetailedDescription("Note 0331");
        criminalCase.setNotes("note 0123");
        criminalCase.setNumber("0968556308");
        criminalCase.setShortDescription("Done");
        criminalCase.setStatus(CaseStatus.valueOf("SUBMITTED"));
        criminalCase.setType(CaseType.valueOf("UNCATEGORIZED"));
        Detective detective  = new Detective();
        detective.setId(1L);
        criminalCase.setLeadInvestigator(detective);
        jdbcCriminal_case.insert(criminalCase);
    }

    @Test
    void update() {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setVersion(3);
        criminalCase.setDetailedDescription("ok");
        criminalCase.setNotes("Note 124112");
        criminalCase.setNumber("012345678");
        criminalCase.setShortDescription("short");
        criminalCase.setStatus(CaseStatus.valueOf("DISMISSED"));
        criminalCase.setType(CaseType.valueOf("MISDEMEANOR"));
        Detective detective  = new Detective();
        detective.setId(1L);
        criminalCase.setLeadInvestigator(detective);
        criminalCase.setId(2L);
        jdbcCriminal_case.update(criminalCase);
        System.out.println("Done");
    }

    @Test
    void delete() {
        jdbcCriminal_case.delete(2L);
    }

    @Test
    void selectAll() {
        System.out.println(jdbcCriminal_case.selectAll());
    }

    @Test
    void selectById() {
        System.out.println(jdbcCriminal_case.selectById(2L));
    }
}