package by.aurorasoft.notificator.crud.repository;

import by.aurorasoft.notificator.base.AbstractSpringBootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

public final class NotificationRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private NotificationRepository repository;

    @Test
    @Sql("classpath:sql/insert-notifications.sql")
    public void notificationShouldBeFoundById() {
        throw new RuntimeException();
    }
}
