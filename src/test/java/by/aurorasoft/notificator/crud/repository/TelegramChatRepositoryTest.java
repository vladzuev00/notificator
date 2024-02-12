package by.aurorasoft.notificator.crud.repository;

import by.aurorasoft.notificator.base.AbstractSpringBootTest;
import by.aurorasoft.notificator.crud.entity.NotificationSourceEntity;
import by.aurorasoft.notificator.crud.entity.TelegramChatEntity;
import by.aurorasoft.notificator.crud.entity.TelegramChatEntity.TelegramUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static by.aurorasoft.notificator.util.TelegramChatEntityUtil.checkEquals;
import static java.time.Instant.parse;
import static org.junit.Assert.assertTrue;

public final class TelegramChatRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private TelegramChatRepository repository;

    @Test
    @Sql("classpath:sql/insert-telegram-chats.sql")
    public void chatShouldBeFoundById() {
        final Long givenId = 255L;

//        reset();
        final Optional<TelegramChatEntity> optionalActual = repository.findById(givenId);
//        assertSelectCount(1);

        assertTrue(optionalActual.isPresent());
        final TelegramChatEntity actual = optionalActual.get();
        final TelegramChatEntity expected = TelegramChatEntity.builder()
                .id(givenId)
                .userId(2)
                .telegramUser(
                        TelegramUser.builder()
                                .id(100)
                                .chatId(100)
                                .firstName("user_100")
                                .language("ru")
                                .build()
                )
                .activated(true)
                .created(parse("2021-04-26T11:34:17Z"))
                .notificationSources(List.of(createNotificationSource(260L)))
                .build();
        checkEquals(expected, actual);
    }

    @Test
    @Sql("classpath:sql/insert-telegram-chats.sql")
    public void chatShouldBeSaved() {
        final TelegramChatEntity givenChat = TelegramChatEntity.builder()
                .userId(2)
                .telegramUser(
                        TelegramUser.builder()
                                .id(100)
                                .chatId(100)
                                .firstName("user_100")
                                .lastName("last_name")
                                .username("username")
                                .language("ru")
                                .build()
                )
                .activated(true)
                .created(parse("2021-04-26T11:34:17Z"))
                .notificationSources(List.of(createNotificationSource(261L)))
                .build();

        //        reset();
        repository.save(givenChat);
        //        assertSelectCount(1);
    }

    private static NotificationSourceEntity createNotificationSource(final Long id) {
        return NotificationSourceEntity.builder()
                .id(id)
                .build();
    }
}
