package com.company.dto;

import java.time.OffsetDateTime;

public class EventDto {
    private Long id;
    private OffsetDateTime created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }
}
