package com.aurorasoft.notificator.crud.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.Instant.now;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "telegram")
public class TelegramChatEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Embedded
    private TelegramUser telegramUser;

    @Column(name = "is_activated")
    private boolean activated;

    @Column(name = "created")
    private Instant created = now();

    @ToString.Exclude
    @ManyToMany(mappedBy = "telegramChats")
    private List<NotificationSourceEntity> notificationSources;

    @PreRemove
    public void preRemove() {
        notificationSources.forEach(s -> s.getTelegramChats().remove(this));
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @EqualsAndHashCode
    @ToString
    @Builder
    @Embeddable
    public static class TelegramUser {
        private static final String DEFAULT_LANGUAGE = "ru";

        @Column(name = "telegram_user_id")
        private long id;

        @Column(name = "telegram_chat_id")
        private long chatId;

        @Column(name = "telegram_user_first_name")
        private String firstName;

        @Column(name = "telegram_user_last_name")
        private String lastName;

        @Column(name = "telegram_username")
        private String username;

        @Column(name = "language")
        private String language = DEFAULT_LANGUAGE;
    }
}
