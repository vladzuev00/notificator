package by.aurorasoft.notificator.util;

import by.aurorasoft.notificator.crud.entity.GeofenceEntity;
import lombok.experimental.UtilityClass;

import static org.junit.Assert.assertEquals;

@UtilityClass
public final class GeofenceEntityUtil {

    public static void checkEquals(final GeofenceEntity expected, final GeofenceEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getColor(), actual.getColor());
        assertEquals(expected.getMaxSpeed(), actual.getMaxSpeed());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getBoundaries(), actual.getBoundaries());
    }
}
