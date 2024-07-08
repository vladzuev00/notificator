package com.aurorasoft.notificator.testutil;

import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import lombok.experimental.UtilityClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UtilityClass
public final class GeofenceEntityUtil {

    public static void checkEquals(GeofenceEntity expected, GeofenceEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getGeometry(), actual.getGeometry());
    }
}
