package by.aurorasoft.notificator.crud.dto;

import by.aurorasoft.notificator.model.UnitStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class Unit {
    Long id;
    String name;
    String color;
    UnitStatus status;
}
