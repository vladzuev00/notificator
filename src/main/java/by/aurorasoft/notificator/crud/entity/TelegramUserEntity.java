package by.aurorasoft.notificator.crud.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public final class TelegramUserEntity extends AbstractEntity<Long> {

    @Column(name = "telegram_user_id")
    private Long Id;

    @Column(name = "telegram_chat_id")
    private Long chatId;

    @Column(name = "telegram_user_first_name")
    private String firstName;

    @Column(name = "telegram_user_last_name")
    private String lastName;

    @Column(name = "telegram_username")
    private String username;

    @Column(name = "language")
    private String language;

//    public void setLanguage(String language) {
//        if (language == null) {
//            this.language = "en";
//        }
//        this.language = language;
//    }
}
