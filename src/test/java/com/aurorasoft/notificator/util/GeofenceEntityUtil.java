package com.aurorasoft.notificator.util;

import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import lombok.experimental.UtilityClass;

import static org.junit.Assert.assertEquals;

@UtilityClass
public final class GeofenceEntityUtil {

    public static void checkEquals(final GeofenceEntity expected, final GeofenceEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getGeometry(), actual.getGeometry());
    }
}
