package com.aurorasoft.notificator.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Embeddable
public class TelegramUserEntity extends AbstractEntity<Long> {

    @Column(name = "telegram_user_id")
    private Long id;

    @Column(name = "telegram_chat_id")
    private long chatId;

    @Column(name = "telegram_user_first_name")
    private String firstName;

    @Column(name = "telegram_user_last_name")
    private String lastName;

    @Column(name = "telegram_username")
    private String username;

    @Column(name = "language")
    private String language;
}
