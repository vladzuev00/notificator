package com.aurorasoft.notificator.crud.repository;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.entity.TimeZoneEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static com.aurorasoft.notificator.util.TimeZoneEntityUtil.checkEquals;
import static com.vladmihalcea.sql.SQLStatementCountValidator.*;
import static org.junit.Assert.assertTrue;

public final class TimeZoneRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private TimeZoneRepository repository;

    @Test
    @Sql("classpath:sql/insert-time-zones.sql")
    public void timeZoneShouldBeFoundById() {
        final Long givenId = 258L;

        reset();
        final Optional<TimeZoneEntity> optionalActual = repository.findById(givenId);
        assertSelectCount(1);

        assertTrue(optionalActual.isPresent());
        final TimeZoneEntity actual = optionalActual.get();
        final TimeZoneEntity expected = TimeZoneEntity.builder()
                .id(givenId)
                .name("Europe/Minsk")
                .build();
        checkEquals(expected, actual);
    }

    @Test
    public void timeZoneShouldBeSaved() {
        final TimeZoneEntity givenTimeZone = TimeZoneEntity.builder()
                .id(255L)
                .name("Europe/Minsk")
                .build();

        reset();
        repository.save(givenTimeZone);
        assertInsertCount(1);
    }
}
