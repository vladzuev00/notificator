package com.aurorasoft.notificator.crud.repository;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static com.aurorasoft.notificator.testutil.GeofenceEntityUtil.checkEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//TODO: count queries
public final class GeofenceRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private GeofenceRepository repository;

    @Autowired
    private GeometryFactory geometryFactory;

    @Test
    @Sql("classpath:sql/insert-geofences.sql")
    public void geofenceShouldBeFoundById() {
        Long givenId = 257L;

        Optional<GeofenceEntity> optionalActual = repository.findById(givenId);
        assertTrue(optionalActual.isPresent());

        GeofenceEntity actual = optionalActual.get();
        GeofenceEntity expected = GeofenceEntity.builder()
                .id(givenId)
                .geometry(
                        geometryFactory.createPolygon(
                                new CoordinateXY[]{
                                        new CoordinateXY(0, 3),
                                        new CoordinateXY(10, 0),
                                        new CoordinateXY(10, 10),
                                        new CoordinateXY(0, 10),
                                        new CoordinateXY(0, 3)
                                }
                        )
                )
                .build();
        checkEquals(expected, actual);
    }

    @Test
    public void geofenceShouldBeSaved() {
        GeofenceEntity givenGeofence = GeofenceEntity.builder()
                .id(255L)
                .geometry(
                        geometryFactory.createPolygon(
                                new CoordinateXY[]{
                                        new CoordinateXY(0, 3),
                                        new CoordinateXY(10, 0),
                                        new CoordinateXY(10, 10),
                                        new CoordinateXY(0, 10),
                                        new CoordinateXY(0, 3)
                                }
                        )
                )
                .build();

        repository.save(givenGeofence);
    }
}
