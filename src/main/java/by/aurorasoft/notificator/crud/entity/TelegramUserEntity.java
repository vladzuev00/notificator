package by.aurorasoft.notificator.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Embeddable
public class TelegramUserEntity extends AbstractEntity<Long> {
    private static final String DEFAULT_LANGUAGE = "ru";

    @Column(name = "telegram_user_id")
    private Long id;

    @Column(name = "telegram_chat_id")
    private Long chatId;

    @Column(name = "telegram_user_first_name")
    private String firstName;

    @Column(name = "telegram_user_last_name")
    private String lastName;

    @Column(name = "telegram_username")
    private String username;

    @Column(name = "language")
    private String language = DEFAULT_LANGUAGE;
}
