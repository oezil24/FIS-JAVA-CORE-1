package com.model;

import com.model.enumerable.EmploymentStatus;
import com.model.enumerable.Rank;
import lombok.Data;

import java.util.Set;
@Data
public class Detective extends AbstractEntity{
    private Person person;
    private String badgeNumber;
    private Rank rank;
    private boolean armed;

    private EmploymentStatus status;
    private Set<CriminalCase> criminalCases;
    private Set<TrackEntry> trackEntries;

    public void replaceWith(Detective detective) {
        this.person = detective.getPerson();
        this.badgeNumber = detective.getBadgeNumber();
        this.rank = detective.getRank();
        this.armed = detective.isArmed();
        this.status = detective.getStatus();
        this.criminalCases = detective.getCriminalCases();
        this.trackEntries = detective.getTrackEntries();
    }
}
