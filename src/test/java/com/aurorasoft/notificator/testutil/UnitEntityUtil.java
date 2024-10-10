package com.aurorasoft.notificator.testutil;

import com.aurorasoft.notificator.crud.entity.UnitEntity;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertSame;

@UtilityClass
public final class UnitEntityUtil {

    public static void assertEquals(UnitEntity expected, UnitEntity actual) {
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getName(), actual.getName());
        Assertions.assertEquals(expected.getColor(), actual.getColor());
        assertSame(expected.getStatus(), actual.getStatus());
    }
}
