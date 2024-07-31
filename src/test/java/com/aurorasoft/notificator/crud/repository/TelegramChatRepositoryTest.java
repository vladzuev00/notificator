package com.aurorasoft.notificator.crud.repository;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.entity.TelegramChatEntity;
import com.aurorasoft.notificator.crud.entity.TelegramUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static com.aurorasoft.notificator.testutil.TelegramChatEntityUtil.checkEquals;
import static java.time.Instant.parse;
import static org.junit.jupiter.api.Assertions.assertTrue;

//TODO: count queries
public final class TelegramChatRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private TelegramChatRepository repository;

    @Test
    @Sql("classpath:sql/insert-telegram-chats.sql")
    public void chatShouldBeFoundById() {
        Long givenId = 257L;

        Optional<TelegramChatEntity> optionalActual = repository.findById(givenId);
        assertTrue(optionalActual.isPresent());

        TelegramChatEntity actual = optionalActual.get();
        TelegramChatEntity expected = new TelegramChatEntity(
                givenId,
                261,
                new TelegramUserEntity(103L, 103, "Sergey", "Shalimo", "user_102", "en"),
                true,
                parse("2021-04-26T11:35:40Z")
        );
        checkEquals(expected, actual);
    }

    @Test
    public void chatShouldBeSaved() {
        TelegramChatEntity givenChat = new TelegramChatEntity(
                255L,
                261,
                new TelegramUserEntity(103L, 103, "Sergey", "Shalimo", "user_102", "en"),
                true,
                parse("2021-04-26T11:35:40Z")
        );

        repository.save(givenChat);
    }
}
