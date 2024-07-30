package com.aurorasoft.notificator.crud.repository;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.entity.TimeZoneEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.aurorasoft.notificator.testutil.TimeZoneEntityUtil.checkEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//TODO: count queries
public final class TimeZoneRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private TimeZoneRepository repository;

    @Test
    public void timeZoneShouldBeFoundById() {
        Long givenId = 255L;

        Optional<TimeZoneEntity> optionalActual = repository.findById(givenId);
        assertTrue(optionalActual.isPresent());

        TimeZoneEntity actual = optionalActual.get();
        TimeZoneEntity expected = new TimeZoneEntity(givenId, "Europe/London");
        checkEquals(expected, actual);
    }

    @Test
    public void timeZoneShouldBeSaved() {
        TimeZoneEntity givenTimeZone = new TimeZoneEntity(1L, "Europe/London");

        repository.save(givenTimeZone);
    }
}
