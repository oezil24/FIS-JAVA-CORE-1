package com.fis.sprint_3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
@Data
@AllArgsConstructor
@EqualsAndHashCode
@MappedSuperclass
public abstract class AbstracEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "version")
    @Version
    protected int version;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected LocalDateTime createdAt;
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    protected LocalDateTime modifiedAt;

    public AbstracEntity() {
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "AbstracEntity{" +
                "id=" + id +
                ", version=" + version +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }

}
