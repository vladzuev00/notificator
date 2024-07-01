package com.aurorasoft.notificator.crud.mapper;

import by.nhorushko.crudgeneric.v2.mapper.AbsMapperEntityDto;
import com.aurorasoft.notificator.crud.dto.TimeZone;
import com.aurorasoft.notificator.crud.entity.TimeZoneEntity;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public final class TimeZoneMapper extends AbsMapperEntityDto<TimeZoneEntity, TimeZone> {

    public TimeZoneMapper(final ModelMapper modelMapper, final EntityManager entityManager) {
        super(modelMapper, entityManager, TimeZoneEntity.class, TimeZone.class);
    }

    @Override
    protected TimeZone create(final TimeZoneEntity entity) {
        return new TimeZone(entity.getId(), entity.getName());
    }
}
