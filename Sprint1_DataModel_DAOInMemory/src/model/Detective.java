package model;

import model.enumerable.EmploymentStatus;
import model.enumerable.Rank;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;


public class Detective extends AbstractEntity{
    private Person person;
    private String badgeNumber;
    private Rank rank;
    private boolean armed;
    private EmploymentStatus status;
    private Set<CriminalCase> criminalCases;
    private Set<TrackEntry> trackEntries;

    public Detective(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, Person person, String badgeNumber, Rank rank, boolean armed, EmploymentStatus status, Set<CriminalCase> criminalCases, Set<TrackEntry> trackEntries) {
        super(id, version, createdAt, modifiedAt);
        this.person = person;
        this.badgeNumber = badgeNumber;
        this.rank = rank;
        this.armed = armed;
        this.status = status;
        this.criminalCases = criminalCases;
        this.trackEntries = trackEntries;
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

    public boolean isArmed() {
        return armed;
    }

    public void setArmed(boolean armed) {
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

    public void setCriminalCases(Set<CriminalCase> criminalCases) {
        this.criminalCases = criminalCases;
    }

    public Set<TrackEntry> getTrackEntries() {
        return trackEntries;
    }

    public void setTrackEntries(Set<TrackEntry> trackEntries) {
        this.trackEntries = trackEntries;
    }

    @Override
    public String toString() {
        return "DetectiveCase{" +
                "person=" + person +
                ", badgeNumber='" + badgeNumber + '\'' +
                ", rank=" + rank +
                ", armed=" + armed +
                ", status=" + status +
                ", criminalCases=" + criminalCases +
                ", trackEntries=" + trackEntries +
                ", id=" + id +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Detective that = (Detective) o;
        return armed == that.armed && person.equals(that.person) && badgeNumber.equals(that.badgeNumber) && rank == that.rank && status == that.status && criminalCases.equals(that.criminalCases) && trackEntries.equals(that.trackEntries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), person, badgeNumber, rank, armed, status, criminalCases, trackEntries);
    }
}
