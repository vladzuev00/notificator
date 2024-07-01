package com.aurorasoft.notificator.util;

import com.aurorasoft.notificator.crud.entity.TimeZoneEntity;
import lombok.experimental.UtilityClass;

import static org.junit.Assert.assertEquals;

@UtilityClass
public final class TimeZoneEntityUtil {

    public static void checkEquals(final TimeZoneEntity expected, final TimeZoneEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
