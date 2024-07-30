package com.aurorasoft.notificator.testutil;

import com.aurorasoft.notificator.crud.entity.TimeZoneEntity;
import lombok.experimental.UtilityClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UtilityClass
public final class TimeZoneEntityUtil {

    public static void checkEquals(TimeZoneEntity expected, TimeZoneEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
