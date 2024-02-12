package by.aurorasoft.notificator.bymessage.mapper;

import by.nhorushko.crudgeneric.mapper.ImmutableDtoAbstractMapper;
import com.locator.server.domain.entity.GeofenceEntity;
import com.locator.server.notification.bymessage.vo.Not_Geofence;
import org.locationtech.jts.geom.Geometry;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public final class Not_GeofenceMapper extends ImmutableDtoAbstractMapper<GeofenceEntity, Not_Geofence> {

    public Not_GeofenceMapper(final ModelMapper modelMapper) {
        super(GeofenceEntity.class, Not_Geofence.class, modelMapper);
    }

    @Override
    protected Not_Geofence mapDto(final GeofenceEntity entity) {
        final Long id = entity.getId();
        final Geometry geometry = entity.getBoundaries();
        return new Not_Geofence(id, geometry);
    }
}
