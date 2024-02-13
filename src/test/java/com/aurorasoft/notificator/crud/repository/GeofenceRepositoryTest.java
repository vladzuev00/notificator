package com.aurorasoft.notificator.crud.repository;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import org.junit.Test;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static com.aurorasoft.notificator.util.GeofenceEntityUtil.checkEquals;
import static io.hypersistence.utils.jdbc.validator.SQLStatementCountValidator.*;
import static org.junit.Assert.assertTrue;

public final class GeofenceRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private GeofenceRepository repository;

    @Autowired
    private GeometryFactory geometryFactory;

    @Test
    @Sql("classpath:sql/insert-geofences.sql")
    public void geofenceShouldBeFoundById() {
        final Long givenId = 257L;

        reset();
        final Optional<GeofenceEntity> optionalActual = repository.findById(givenId);
        assertSelectCount(1);

        assertTrue(optionalActual.isPresent());
        final GeofenceEntity actual = optionalActual.get();
        final GeofenceEntity expected = GeofenceEntity.builder()
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
        final GeofenceEntity givenGeofence = GeofenceEntity.builder()
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

        reset();
        repository.save(givenGeofence);
        assertInsertCount(1);
    }
}
