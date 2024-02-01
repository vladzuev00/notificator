package by.aurorasoft.notificator.crud.repository;

import by.aurorasoft.notificator.base.AbstractSpringBootTest;
import by.aurorasoft.notificator.crud.entity.UnitEntity;
import by.aurorasoft.notificator.repository.UnitRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static by.aurorasoft.notificator.model.UnitStatus.ACTIVE;
import static by.aurorasoft.notificator.util.UnitEntityUtil.checkEquals;
import static org.junit.Assert.assertTrue;

public final class UnitRepositoryTest extends AbstractSpringBootTest {

    @Autowired
    private UnitRepository repository;

    @Test
    public void unitShouldBeFoundById() {
        final Long givenId = 255L;

        //TODO        reset();
        final Optional<UnitEntity> optionalActual = repository.findById(givenId);
        //TODO       assertSelectCount(1);

        assertTrue(optionalActual.isPresent());
        final UnitEntity actual = optionalActual.get();
        final UnitEntity expected = UnitEntity.builder()
                .id(givenId)
                .name("Mercedes АР 6575-7")
                .color("D73816")
                .status(ACTIVE)
                .build();
        checkEquals(expected, actual);
    }

    @Test
    public void unitShouldBeSaved() {
        final UnitEntity givenUnit = UnitEntity.builder()
                .name("Mercedes АР 6575-7")
                .color("D73816")
                .status(ACTIVE)
                .build();

        //TODO        reset();
        repository.save(givenUnit);
        //TODO        assertInsertCount(1);
    }
}
