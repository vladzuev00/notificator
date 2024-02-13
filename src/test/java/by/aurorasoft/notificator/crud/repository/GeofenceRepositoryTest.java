package by.aurorasoft.notificator.crud.repository;

import by.aurorasoft.notificator.base.AbstractSpringBootTest;
import by.aurorasoft.notificator.crud.entity.GeofenceEntity;
import org.junit.Test;
import org.locationtech.jts.geom.CoordinateXY;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static by.aurorasoft.notificator.util.GeofenceEntityUtil.checkEquals;
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
        final Long givenId = 3L;

        reset();
        final Optional<GeofenceEntity> optionalActual = repository.findById(givenId);
        assertSelectCount(1);

        assertTrue(optionalActual.isPresent());
        final GeofenceEntity actual = optionalActual.get();
        final GeofenceEntity expected = GeofenceEntity.builder()
                .id(givenId)
                .name("test-geo10.0")
                .description("description10.0")
                .color("#111111")
                .maxSpeed(2)
                .userId(2)
                .boundaries(
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
                .name("test-geo10.0")
                .description("description10.0")
                .color("#111111")
                .maxSpeed(2)
                .userId(2)
                .boundaries(
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
