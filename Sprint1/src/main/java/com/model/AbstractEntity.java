package model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * All classes have to "extends" from AbstractEntity
 * Contain 4 common fields: id, version, createdAt, modifiedAt
 */
public abstract class AbstractEntity {
    /**
     * id: not null, unique, distinct
     * @java,utils.UUID ?
     * Usage: to identify uniquely each entity instance
     */
    protected long id;
    /**
     * version: not null, start from 0;
     * usage: keep track of how many times an entity was modified
     */
    protected int version;
    /**
     * createdAt: not null, LocalDateTime.now() to make
     * usage: audit each entity instance by created time
     */
    protected LocalDateTime createdAt;
    /**
     * modifiedAT: not null, LocalDateTime.now() to make
     * usage: audit each entity instance by modified time
     */
    protected LocalDateTime modifiedAt;

    /**
     * Construtor with full instance
     * @param id
     * @param version
     * @param createdAt
     * @param modifiedAt
     */
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
