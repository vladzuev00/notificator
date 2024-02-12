package by.aurorasoft.notificator.crud.mapper;

import by.aurorasoft.notificator.base.AbstractSpringBootTest;
import by.aurorasoft.notificator.crud.dto.Unit;
import by.aurorasoft.notificator.crud.entity.UnitEntity;
import by.aurorasoft.notificator.model.UnitStatus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.aurorasoft.notificator.model.UnitStatus.ACTIVE;
import static by.aurorasoft.notificator.util.UnitEntityUtil.checkEquals;
import static org.junit.Assert.assertEquals;

public final class UnitMapperTest extends AbstractSpringBootTest {

    @Autowired
    private UnitMapper mapper;

    @Test
    public void entityShouldBeMappedToDto() {
        final Long givenId = 255L;
        final String givenName = "name";
        final String givenColor = "color";
        final UnitStatus givenStatus = ACTIVE;
        final UnitEntity givenEntity = UnitEntity.builder()
                .id(givenId)
                .name(givenName)
                .color(givenColor)
                .status(givenStatus)
                .build();

        final Unit actual = mapper.toDto(givenEntity);
        final Unit expected = Unit.builder()
                .id(givenId)
                .name(givenName)
                .color(givenColor)
                .status(givenStatus)
                .build();
        assertEquals(expected, actual);
    }

    @Test
    public void dtoShouldBeMappedToEntity() {
        final Long givenId = 255L;
        final String givenName = "name";
        final String givenColor = "color";
        final UnitStatus givenStatus = ACTIVE;
        final Unit givenDto = Unit.builder()
                .id(givenId)
                .name(givenName)
                .color(givenColor)
                .status(givenStatus)
                .build();

        final UnitEntity actual = mapper.toEntity(givenDto);
        final UnitEntity expected = UnitEntity.builder()
                .id(givenId)
                .name(givenName)
                .color(givenColor)
                .status(givenStatus)
                .build();
        checkEquals(expected, actual);
    }
}
