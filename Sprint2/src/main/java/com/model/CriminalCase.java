package com.model;

import com.model.enumerable.CaseStatus;
import com.model.enumerable.CaseType;

import java.util.Set;

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
}
