package com.model;

import com.model.enumerable.CaseStatus;
import com.model.enumerable.CaseType;
import lombok.Data;

import java.util.Set;
@Data
public class CriminalCase extends AbstractEntity{
    private String number;
    private CaseType type;
    private String shortDescription;
    private String detailedDescription;
    private CaseStatus status;
    private String notes;
    private Set<Evidence> evidenceSet;
    private Detective leadInvestigator;
    private Set<Detective> assigned;
    public void replaceWith(CriminalCase criminalCase) {
        this.number = criminalCase.getNumber();
        this.type = criminalCase.getType();
        this.shortDescription = criminalCase.getShortDescription();
        this.detailedDescription = criminalCase.getDetailedDescription();
        this.status = criminalCase.getStatus();
        this.notes = criminalCase.getNotes();
        this.evidenceSet = criminalCase.getEvidenceSet();
        this.leadInvestigator = criminalCase.getLeadInvestigator();
        this.assigned = criminalCase.getAssigned();
    }

    @Override
    public String toString() {
        return "CriminalCase{" +
                "number='" + number + '\'' +
                ", type=" + type +
                ", shortDescription='" + shortDescription + '\'' +
                ", detailedDescription='" + detailedDescription + '\'' +
                ", status=" + status +
                ", notes='" + notes + '\'' +
                ", evidenceSet=" + evidenceSet +
                ", leadInvestigator=" + leadInvestigator +
                ", assigned=" + assigned +
                ", id=" + id +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
