package fis.com.sprint4.service.impl;

import fis.com.sprint4.entity.CriminalCase;
import fis.com.sprint4.repository.CriminalCaseRepository;
import fis.com.sprint4.service.CriminalCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CriminalCaseServiceImpl implements CriminalCaseService {

    @Autowired
    CriminalCaseRepository criminalCaseRepository;


    @Override
    public CriminalCase addCriminalCase(CriminalCase criminalCase) {
        return this.criminalCaseRepository.save(criminalCase);
    }

    @Override
    public CriminalCase updateCriminalCase(CriminalCase criminalCase) {
        return this.criminalCaseRepository.save(criminalCase);
    }

    @Override
    public Set<CriminalCase> getCriminalCases() {
        return new HashSet<>(this.criminalCaseRepository.findAll());
    }

    @Override
    public CriminalCase getCriminalCase(Long id) {
        return this.criminalCaseRepository.findById(id).get();
    }

    @Override
    public void deleteCriminalCase(Long id) {
        CriminalCase criminalCase = new CriminalCase();
        criminalCase.setId(id);
        this.criminalCaseRepository.delete(criminalCase);
    }
}
