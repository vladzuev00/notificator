package by.aurorasoft.notificator.crud.dto;

import by.aurorasoft.notificator.model.UnitStatus;
import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class Unit implements AbstractDto<Long> {
    Long id;
    String name;
    String color;
    UnitStatus status;
}
