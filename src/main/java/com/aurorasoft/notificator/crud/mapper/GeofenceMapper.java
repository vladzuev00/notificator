package com.aurorasoft.notificator.crud.mapper;

import com.aurorasoft.notificator.crud.dto.Geofence;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import by.nhorushko.crudgeneric.v2.mapper.AbsMapperEntityDto;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public final class GeofenceMapper extends AbsMapperEntityDto<GeofenceEntity, Geofence> {

    public GeofenceMapper(final ModelMapper modelMapper, final EntityManager entityManager) {
        super(modelMapper, entityManager, GeofenceEntity.class, Geofence.class);
    }

    @Override
    protected Geofence create(final GeofenceEntity entity) {
        return new Geofence(entity.getId(), entity.getGeometry());
    }
}
