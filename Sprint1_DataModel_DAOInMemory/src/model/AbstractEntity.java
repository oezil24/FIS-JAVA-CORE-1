package model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * All classes have to "extends" from AbstractEntity
 * Contain 4 common fields: id, version, createdAt, modifiedAt
 */
public abstract class AbstractEntity {

    protected long id;

    protected int version;

    protected LocalDateTime createdAt;

    protected LocalDateTime modifiedAt;

    public AbstractEntity(long id, int version, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.version = version;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id && version == that.version && createdAt.equals(that.createdAt) && modifiedAt.equals(that.modifiedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, createdAt, modifiedAt);
    }

}
