package com.company.dto;

import java.time.OffsetDateTime;

public interface ItemDbDto {

    Long getId();

    int getSort();

    OffsetDateTime getCreated();

    Boolean getTicked();

}
