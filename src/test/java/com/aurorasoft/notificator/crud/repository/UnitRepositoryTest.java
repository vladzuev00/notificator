package com.aurorasoft.notificator.crud.repository;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.entity.UnitEntity;
import com.aurorasoft.notificator.model.UnitStatus;
import com.aurorasoft.notificator.util.UnitEntityUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.vladmihalcea.sql.SQLStatementCountValidator.*;
import static org.junit.Assert.assertTrue;

public final class UnitRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private UnitRepository repository;

    @Test
    public void unitShouldBeFoundById() {
        final Long givenId = 255L;

        reset();
        final Optional<UnitEntity> optionalActual = repository.findById(givenId);
        assertSelectCount(1);

        assertTrue(optionalActual.isPresent());
        final UnitEntity actual = optionalActual.get();
        final UnitEntity expected = UnitEntity.builder()
                .id(givenId)
                .name("Mercedes АР 6575-7")
                .color("D73816")
                .status(UnitStatus.ACTIVE)
                .build();
        UnitEntityUtil.checkEquals(expected, actual);
    }

    @Test
    public void unitShouldBeSaved() {
        final UnitEntity givenUnit = UnitEntity.builder()
                .id(255L)
                .name("Mercedes АР 6575-7")
                .color("D73816")
                .status(UnitStatus.ACTIVE)
                .build();

        reset();
        repository.save(givenUnit);
        assertInsertCount(1);
    }
}
