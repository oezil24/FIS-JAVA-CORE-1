package model;

import model.enumerable.TrackAction;

import java.time.LocalDateTime;
import java.util.Objects;

public class TrackEntry extends AbstractEntity{
    protected LocalDateTime date;
    private Evidence evidence;
    private Detective detective;
    private TrackAction action;
    private String reason;

    public TrackEntry(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime date, Evidence evidence, Detective detective, TrackAction action, String reason) {
        super(id, version, createdAt, modifiedAt);
        this.date = date;
        this.evidence = evidence;
        this.detective = detective;
        this.action = action;
        this.reason = reason;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Evidence getEvidence() {
        return evidence;
    }

    public void setEvidence(Evidence evidence) {
        this.evidence = evidence;
    }

    public Detective getDetective() {
        return detective;
    }

    public void setDetective(Detective detective) {
        this.detective = detective;
    }

    public TrackAction getAction() {
        return action;
    }

    public void setAction(TrackAction action) {
        this.action = action;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "TrackEntry{" +
                "date=" + date +
                ", evidence=" + evidence +
                ", detective=" + detective +
                ", action=" + action +
                ", reason='" + reason + '\'' +
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
        TrackEntry that = (TrackEntry) o;
        return date.equals(that.date) && evidence.equals(that.evidence) && detective.equals(that.detective) && action == that.action && reason.equals(that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), date, evidence, detective, action, reason);
    }
}
