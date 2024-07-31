package com.aurorasoft.notificator.testutil;

import com.aurorasoft.notificator.crud.entity.TelegramChatEntity;
import lombok.experimental.UtilityClass;

import static org.junit.jupiter.api.Assertions.assertEquals;

@UtilityClass
public final class TelegramChatEntityUtil {

    public static void checkEquals(TelegramChatEntity expected, TelegramChatEntity actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUserId(), actual.getUserId());
        TelegramUserEntityUtil.checkEquals(expected.getTelegramUser(), actual.getTelegramUser());
        assertEquals(expected.isActivated(), actual.isActivated());
        assertEquals(expected.getCreated(), actual.getCreated());
    }
}
