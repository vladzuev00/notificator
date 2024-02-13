package com.aurorasoft.notificator.crud.mapper;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.dto.Geofence;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import org.junit.Test;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static com.aurorasoft.notificator.util.GeofenceEntityUtil.checkEquals;
import static org.junit.Assert.assertEquals;

public final class GeofenceMapperTest extends AbstractSpringBootTest {

    @Autowired
    private GeofenceMapper mapper;

    @Autowired
    private GeometryFactory geometryFactory;

    @Test
    public void entityShouldBeMappedToDto() {
        final Long givenId = 255L;
        final Geometry givenGeometry = geometryFactory.createPolygon(
                new CoordinateXY[]{
                        new CoordinateXY(0, 3),
                        new CoordinateXY(10, 0),
                        new CoordinateXY(10, 10),
                        new CoordinateXY(0, 10),
                        new CoordinateXY(0, 3)
                }
        );
        final GeofenceEntity givenEntity = GeofenceEntity.builder()
                .id(givenId)
                .geometry(givenGeometry)
                .build();

        final Geofence actual = mapper.toDto(givenEntity);
        final Geofence expected = Geofence.builder()
                .id(givenId)
                .geometry(givenGeometry)
                .build();
        assertEquals(expected, actual);
    }

    @Test
    public void dtoShouldBeMappedToEntity() {
        final Long givenId = 255L;
        final Geometry givenGeometry = geometryFactory.createPolygon(
                new CoordinateXY[]{
                        new CoordinateXY(0, 3),
                        new CoordinateXY(10, 0),
                        new CoordinateXY(10, 10),
                        new CoordinateXY(0, 10),
                        new CoordinateXY(0, 3)
                }
        );
        final Geofence givenDto = Geofence.builder()
                .id(givenId)
                .geometry(givenGeometry)
                .build();

        final GeofenceEntity actual = mapper.toEntity(givenDto);
        final GeofenceEntity expected = GeofenceEntity.builder()
                .id(givenId)
                .geometry(givenGeometry)
                .build();
        checkEquals(expected, actual);
    }
}
