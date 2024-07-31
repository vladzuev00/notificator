package com.aurorasoft.notificator.testutil;

import com.aurorasoft.notificator.crud.entity.TelegramUserEntity;
import lombok.experimental.UtilityClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UtilityClass
public final class TelegramUserEntityUtil {

    public static void checkEquals(TelegramUserEntity expected, TelegramUserEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getChatId(), actual.getChatId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getLanguage(), actual.getLanguage());
    }
}
