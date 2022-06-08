package com.model;

import java.time.LocalDateTime;

public abstract class AbstractEntity {
    protected long id;

    protected int version;

    protected LocalDateTime createdAt;

    protected LocalDateTime modifiedAt;
}
