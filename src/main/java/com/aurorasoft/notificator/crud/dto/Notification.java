package com.aurorasoft.notificator.crud.dto;

import com.aurorasoft.notificator.model.NotificationStatus;
import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
@AllArgsConstructor
@Builder
public class Notification implements AbstractDto<Long> {
    Long id;
    Unit unit;
    NotificationSource source;
    Instant startTime;
    Instant finishTime;
    NotificationStatus status;
}
