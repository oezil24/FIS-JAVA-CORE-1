package com.fis.spring3.model;

import com.fis.spring3.core.EmploymentStatus;
import com.fis.spring3.core.Rank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Detective extends AbstractEntity {
    private Person person;
    private String badgeNumber;
    private Rank rank;
    private Boolean armed = false;
    private EmploymentStatus status = EmploymentStatus.ACTIVE;
    private Set<CriminalCase> criminalCases = new HashSet<>();
    private Set<TrackEntry> trackEntries;

    public Detective() {
        super();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Boolean getArmed() {
        return armed;
    }

    public void setArmed(Boolean armed) {
        this.armed = armed;
    }

    public EmploymentStatus getStatus() {
        return status;
    }

    public void setStatus(EmploymentStatus status) {
        this.status = status;
    }

    public Set<CriminalCase> getCriminalCases() {
        return criminalCases;
    }

    private void setCriminalCases(Set<CriminalCase> criminalCases) {
        this.criminalCases = criminalCases;
    }

    boolean addCase(CriminalCase criminalCase){
        return criminalCases.add(criminalCase);
    }

    public Set<TrackEntry> getTrackEntries() {
        return trackEntries;
    }

    private void setTrackEntries(Set<TrackEntry> trackEntries) {
        this.trackEntries = trackEntries;
    }

    public boolean addTrackEntry(TrackEntry trackEntry){
        trackEntry.setDetective(this);
        return trackEntries.add(trackEntry);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Detective detective = (Detective) o;
        return Objects.equals(person, detective.person) &&
                Objects.equals(badgeNumber, detective.badgeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), person, badgeNumber);
    }

    @Override
    public String toString() {
        return String.format("Detective\n\t[person='%s', badgeNumber='%s', rank='%s', armed='%s', status='%s']",
                person.toString(), badgeNumber,rank, armed, status);
    }
}
