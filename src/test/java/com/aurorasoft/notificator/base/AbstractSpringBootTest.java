package com.aurorasoft.notificator.base;

import com.aurorasoft.notificator.base.containerinitializer.DataBaseContainerInitializer;
import com.aurorasoft.notificator.base.containerinitializer.KafkaContainerInitializer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static java.time.ZoneOffset.UTC;
import static java.util.TimeZone.getTimeZone;
import static java.util.TimeZone.setDefault;

@Transactional
@SpringBootTest
@ContextConfiguration(initializers = {DataBaseContainerInitializer.class, KafkaContainerInitializer.class})
public abstract class AbstractSpringBootTest {

    @PersistenceContext
    protected EntityManager entityManager;

    @BeforeAll
    public static void setDefaultTimeZone() {
        setDefault(getTimeZone(UTC));
    }
}
