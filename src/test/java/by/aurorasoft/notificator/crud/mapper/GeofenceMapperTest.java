package by.aurorasoft.notificator.crud.mapper;

import by.aurorasoft.notificator.base.AbstractSpringBootTest;
import by.aurorasoft.notificator.crud.dto.Geofence;
import by.aurorasoft.notificator.crud.entity.GeofenceEntity;
import org.junit.Test;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static by.aurorasoft.notificator.util.GeofenceEntityUtil.checkEquals;
import static org.junit.Assert.assertEquals;

public final class GeofenceMapperTest extends AbstractSpringBootTest {

    @Autowired
    private GeofenceMapper mapper;

    @Autowired
    private GeometryFactory geometryFactory;

    @Test
    public void entityShouldBeMappedToDto() {
        final Long givenId = 255L;
        final String givenName = "name";
        final String givenDescription = "description";
        final String givenColor = "color";
        final int givenMaxSpeed = 120;
        final long givenUserId = 256;
        final Geometry givenBoundaries = geometryFactory.createPolygon(
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
                .name(givenName)
                .description(givenDescription)
                .color(givenColor)
                .maxSpeed(givenMaxSpeed)
                .userId(givenUserId)
                .boundaries(givenBoundaries)
                .build();

        final Geofence actual = mapper.toDto(givenEntity);
        final Geofence expected = Geofence.builder()
                .id(givenId)
                .name(givenName)
                .description(givenDescription)
                .color(givenColor)
                .maxSpeed(givenMaxSpeed)
                .userId(givenUserId)
                .boundaries(givenBoundaries)
                .build();
        assertEquals(expected, actual);
    }

    @Test
    public void dtoShouldBeMappedToEntity() {
        final Long givenId = 255L;
        final String givenName = "name";
        final String givenDescription = "description";
        final String givenColor = "color";
        final int givenMaxSpeed = 120;
        final long givenUserId = 256;
        final Geometry givenBoundaries = geometryFactory.createPolygon(
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
                .name(givenName)
                .description(givenDescription)
                .color(givenColor)
                .maxSpeed(givenMaxSpeed)
                .userId(givenUserId)
                .boundaries(givenBoundaries)
                .build();

        final GeofenceEntity actual = mapper.toEntity(givenDto);
        final GeofenceEntity expected = GeofenceEntity.builder()
                .id(givenId)
                .name(givenName)
                .description(givenDescription)
                .color(givenColor)
                .maxSpeed(givenMaxSpeed)
                .userId(givenUserId)
                .boundaries(givenBoundaries)
                .build();
        checkEquals(expected, actual);
    }
}
