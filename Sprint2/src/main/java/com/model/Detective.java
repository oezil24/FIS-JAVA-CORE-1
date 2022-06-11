package com.model;

import com.model.enumerable.EmploymentStatus;
import com.model.enumerable.Rank;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
@Data
public class Detective extends AbstractEntity{
    private String badgeNumber;
    private Rank rank;
    private boolean armed;

    private EmploymentStatus status;
    private Set<CriminalCase> criminalCases;
    private Set<TrackEntry> trackEntries;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime hiringDate;

    public void replaceWith(Detective detective) {
        this.badgeNumber = detective.getBadgeNumber();
        this.rank = detective.getRank();
        this.armed = detective.isArmed();
        this.status = detective.getStatus();
        this.criminalCases = detective.getCriminalCases();
        this.trackEntries = detective.getTrackEntries();
        username = detective.getUsername();
        firstName = detective.getFirstName();
        lastName = detective.getLastName();
        password = detective.getPassword();
        hiringDate =detective.getHiringDate();
    }
}
