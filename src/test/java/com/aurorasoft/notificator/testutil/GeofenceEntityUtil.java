package com.aurorasoft.notificator.testutil;

import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.Assertions;

@UtilityClass
public final class GeofenceEntityUtil {

    public static void assertEquals(GeofenceEntity expected, GeofenceEntity actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getGeometry(), actual.getGeometry());
    }
}
