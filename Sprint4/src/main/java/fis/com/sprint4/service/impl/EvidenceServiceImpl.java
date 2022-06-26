package fis.com.sprint4.service.impl;

import fis.com.sprint4.entity.Evidence;
import fis.com.sprint4.repository.EvidenceRepository;
import fis.com.sprint4.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EvidenceServiceImpl implements EvidenceService {

    @Autowired
    EvidenceRepository evidenceRepository;

    @Override
    public Evidence addEvidence(Evidence evidence) {
        return this.evidenceRepository.save(evidence);
    }

    @Override
    public Evidence updateEvidence(Evidence evidence) {
        return this.evidenceRepository.save(evidence);
    }

    @Override
    public Set<Evidence> getEvidences() {
        return new HashSet<>(this.evidenceRepository.findAll());
    }

    @Override
    public Evidence getEvidence(Long id) {
        return this.evidenceRepository.findById(id).get();
    }

    @Override
    public void deleteEvidence(Long id) {
        Evidence evidence = new Evidence();
        evidence.setId(id);
        this.evidenceRepository.delete(evidence);
    }
}
