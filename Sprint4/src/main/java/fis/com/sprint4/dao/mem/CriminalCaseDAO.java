package fis.com.sprint4.dao.mem;



import fis.com.sprint4.dao.DAO;
import fis.com.sprint4.entity.CriminalCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class CriminalCaseDAO implements DAO<CriminalCase> {
    private final List<CriminalCase> criminalCases = new ArrayList<>();

    @Override
    public List<CriminalCase> getAll() {
        return criminalCases;
    }

    @Override
    public Optional<CriminalCase> get(Long id) {
        return criminalCases.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    @Override
    public void save(CriminalCase criminalCase) {
        criminalCases.add(criminalCase);
    }

    @Override
    public void update(CriminalCase criminalCase) {
        get(criminalCase.getId()).ifPresent(e -> {
            e.setNumber(e.getNumber());
            e.setType(e.getType());
            e.setShortDescription(e.getShortDescription());
            e.setDetailedDescription(e.getDetailedDescription());
            e.setStatus(e.getStatus());
            e.setNotes(e.getNotes());
            e.setLeadInvestigator(e.getLeadInvestigator());
        });
    }

    @Override
    public void delete(CriminalCase criminalCase) {
        get(criminalCase.getId()).ifPresent(criminalCases::remove);
    }

    @Override
    public void deleteId(Long id) {

    }
}
