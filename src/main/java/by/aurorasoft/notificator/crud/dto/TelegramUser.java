package by.aurorasoft.notificator.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder
public class TelegramUser {
    Long id;
    Long chatId;
    String firstName;
    String lastName;
    String username;
    String language;
}
