package com.aurorasoft.notificator.util;

import com.aurorasoft.notificator.crud.entity.NotificationEntity;
import lombok.experimental.UtilityClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@UtilityClass
public final class NotificationEntityUtil {

    public static void checkEquals(final NotificationEntity expected, final NotificationEntity actual) {
        assertEquals(expected, actual);
        assertEquals(expected.getUnit(), actual.getUnit());
        assertEquals(expected.getSource(), actual.getSource());
        assertEquals(expected.getStartTime(), actual.getStartTime());
        assertEquals(expected.getFinishTime(), actual.getFinishTime());
        assertSame(expected.getStatus(), actual.getStatus());
        assertEquals(expected.isRead(), actual.isRead());
        assertEquals(expected.getCreateTime(), actual.getCreateTime());
    }
}
