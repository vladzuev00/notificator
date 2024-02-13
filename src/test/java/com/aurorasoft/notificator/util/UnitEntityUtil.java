package com.aurorasoft.notificator.util;

import com.aurorasoft.notificator.crud.entity.UnitEntity;
import lombok.experimental.UtilityClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@UtilityClass
public final class UnitEntityUtil {

    public static void checkEquals(final UnitEntity expected, final UnitEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getColor(), actual.getColor());
        assertSame(expected.getStatus(), actual.getStatus());
    }
}
