package com.aurorasoft.notificator.util;

import com.aurorasoft.notificator.crud.entity.NotificationSourceEntity;
import com.aurorasoft.notificator.crud.entity.TelegramChatEntity;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@UtilityClass
public final class TelegramChatEntityUtil {

    public static void checkEquals(final TelegramChatEntity expected, final TelegramChatEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUserId(), actual.getUserId());
        assertEquals(expected.getTelegramUser(), actual.getTelegramUser());
        assertEquals(expected.isActivated(), actual.isActivated());
        checkEqualsWithoutOrder(expected.getNotificationSources(), actual.getNotificationSources());
        assertEquals(expected.getCreated(), actual.getCreated());
    }

    private static void checkEqualsWithoutOrder(final List<NotificationSourceEntity> expected,
                                                final List<NotificationSourceEntity> actual) {
        if (expected != null && actual != null) {
            assertEquals(new HashSet<>(expected), new HashSet<>(actual));
        } else {
            assertTrue(expected == null && actual == null);
        }
    }
}
