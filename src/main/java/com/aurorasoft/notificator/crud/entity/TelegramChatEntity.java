package com.aurorasoft.notificator.crud.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "telegram")
public class TelegramChatEntity extends AbstractEntity<Long> {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private int userId;

    @Embedded
    TelegramUserEntity telegramUser;

    @Column(name = "activated")
    boolean activated;

    @Column(name = "created")
    Instant created;
}
