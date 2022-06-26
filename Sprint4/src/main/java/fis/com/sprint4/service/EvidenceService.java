package fis.com.sprint4.service;

import fis.com.sprint4.entity.Evidence;

import java.util.Set;

public interface EvidenceService {
    Evidence addEvidence(Evidence evidence);

    Evidence updateEvidence(Evidence evidence);

    Set<Evidence> getEvidences();

    Evidence getEvidence(Long id);

    void deleteEvidence(Long id);

}
