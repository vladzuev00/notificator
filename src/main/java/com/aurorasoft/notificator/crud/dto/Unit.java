package com.aurorasoft.notificator.crud.dto;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.time.ZoneId;

@Value
public class Unit implements AbstractDto<Long> {
    Long id;
    String name;
    ZoneId zoneId;

    @Builder
    @JsonCreator
    public Unit(@JsonProperty("id") final Long id,
                @JsonProperty("name") final String name,
                @JsonProperty("zoneId") final ZoneId zoneId) {
        this.id = id;
        this.name = name;
        this.zoneId = zoneId;
    }
}
