package by.aurorasoft.notificator.crud.entity;

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
    private Long id;

    @Column(name = "user_id")
    private long userId;

    @Embedded
    private TelegramUser telegramUser;

    @Column(name = "is_activated")
    private boolean activated;

    @ToString.Exclude
    @ManyToMany(mappedBy = "telegramChats")
    private List<NotificationSourceEntity> notificationSources;

    @Column(name = "created")
    private Instant created = now();

    @PreRemove
    public void preRemove(){
        notificationSources.forEach(s -> s.getTelegramChats().remove(this));
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    @Getter
    @ToString
    @Builder
    @Embeddable
    public static class TelegramUser {
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
}
