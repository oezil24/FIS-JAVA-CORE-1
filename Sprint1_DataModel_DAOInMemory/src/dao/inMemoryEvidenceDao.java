package dao;

import model.Evidence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class inMemoryEvidenceDao implements inMemoryDAO<Evidence>{

    private Map<Long, Evidence> evidenceMap = new HashMap<>();
    @Override
    public Evidence insertOrUpdate(Evidence evidence) {
        evidenceMap.put(evidence.getId(),evidence);
        return evidence;
    }

    @Override
    public List<Evidence> findAll() {
        return new ArrayList<>(evidenceMap.values());
    }

    @Override
    public void deleteById(Long id) {
        evidenceMap.remove(id);
    }
}
