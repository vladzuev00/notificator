package by.aurorasoft.notificator.crud.dto;

import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;

@Value
public class TelegramChat implements AbstractDto<Long> {
    Long id;
    long userId;
    TelegramUser telegramUser;
    boolean isActivated;
    Instant created;

    @Value
    @AllArgsConstructor
    @Builder
    public static class TelegramUser {
        Long id;
        Long chatId;
        String firstName;
        String lastName;
        String username;
        String language;
    }
}
