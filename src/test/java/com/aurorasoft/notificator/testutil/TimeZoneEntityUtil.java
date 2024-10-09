package com.aurorasoft.notificator.testutil;

import com.aurorasoft.notificator.crud.entity.TimeZoneEntity;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.Assertions;

@UtilityClass
public final class TimeZoneEntityUtil {

    public static void assertEquals(TimeZoneEntity expected, TimeZoneEntity actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
    }
}
