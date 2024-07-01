package com.aurorasoft.notificator.crud.dto;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class TimeZone implements AbstractDto<Long> {
    Long id;
    String name;
}
