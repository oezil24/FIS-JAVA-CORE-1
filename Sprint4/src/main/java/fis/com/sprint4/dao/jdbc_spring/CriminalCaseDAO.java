package fis.com.sprint4.dao.jdbc_spring;

import fis.com.sprint4.entity.CriminalCase;
import java.util.List;

public interface CriminalCaseDAO {
    void saveOrUpdate(CriminalCase criminalCase);

    void delete(Long id);

    CriminalCase getCriminalCase(Long id);

    List<CriminalCase> listCriminalCases();
}
