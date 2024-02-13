package com.aurorasoft.notificator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class UnitAction {
    long unitId;
    String type;
    String message;
    long timeSeconds;
}
