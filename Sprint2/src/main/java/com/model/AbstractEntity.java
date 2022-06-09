package com.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public abstract class AbstractEntity {
    protected long id;

    protected int version;

    protected LocalDateTime createdAt;

    protected LocalDateTime modifiedAt;

    public void replaceWith(AbstractEntity abstractEntity) {
        id = abstractEntity.getId();
        version = abstractEntity.getVersion();
        createdAt = abstractEntity.getCreatedAt();
        modifiedAt =abstractEntity.getModifiedAt();
    }
}
