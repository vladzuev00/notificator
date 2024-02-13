package com.aurorasoft.notificator.crud.service;

import com.aurorasoft.notificator.crud.dto.Geofence;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import com.aurorasoft.notificator.crud.mapper.GeofenceMapper;
import com.aurorasoft.notificator.crud.repository.GeofenceRepository;
import by.nhorushko.crudgeneric.v2.service.AbsServiceCRUD;
import org.springframework.stereotype.Service;

@Service
public class GeofenceService extends AbsServiceCRUD<Long, GeofenceEntity, Geofence, GeofenceRepository> {

    public GeofenceService(final GeofenceMapper mapper, final GeofenceRepository repository) {
        super(mapper, repository);
    }

}
