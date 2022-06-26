package fis.com.sprint4.service;

import fis.com.sprint4.entity.CriminalCase;

import java.util.Set;

public interface CriminalCaseService {
    CriminalCase addCriminalCase(CriminalCase criminalCase);

    CriminalCase updateCriminalCase(CriminalCase criminalCase);

    Set<CriminalCase> getCriminalCases();

    CriminalCase getCriminalCase(Long id);

    void deleteCriminalCase(Long id);

}
