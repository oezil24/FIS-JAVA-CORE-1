package fis.com.sprint4.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import fis.com.sprint4.util.CaseStatus;
import fis.com.sprint4.util.CaseType;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriminalCase extends AbstractEntity{
    private String number;

    @NotNull
    @Column(name="case_type")
    @Enumerated(EnumType.STRING)
    private CaseType type;

    private String shortDescription;
    private String detailedDescription;

    @Enumerated(EnumType.STRING)
    private CaseStatus status;

    private String notes;

    @OneToMany(mappedBy = "criminalCase", cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Set<Evidence> evidences = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "LEAD_INVESTIGATOR", nullable = false)
    private Detective leadInvestigator;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name="working_detective_case",
            joinColumns=@JoinColumn(name="case_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="detective_id", referencedColumnName="id"))
    private Set<Detective> assigned = new HashSet<>();


}
