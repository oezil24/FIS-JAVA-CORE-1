package com.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public abstract class AbstractEntity {
    protected long id;

    protected int version;

    protected LocalDateTime createdAt;

    protected LocalDateTime modifiedAt;
}
