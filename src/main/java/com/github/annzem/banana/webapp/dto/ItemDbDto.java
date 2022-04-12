package com.github.annzem.banana.webapp.dto;

import java.time.OffsetDateTime;

public interface ItemDbDto {

    Long getId();

    int getSort();

    OffsetDateTime getCreated();

    Boolean getTicked();

}
