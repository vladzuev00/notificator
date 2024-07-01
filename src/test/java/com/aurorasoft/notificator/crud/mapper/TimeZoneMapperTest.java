package com.aurorasoft.notificator.crud.mapper;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.dto.TimeZone;
import com.aurorasoft.notificator.crud.entity.TimeZoneEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.aurorasoft.notificator.util.TimeZoneEntityUtil.checkEquals;
import static org.junit.Assert.assertEquals;

public final class TimeZoneMapperTest extends AbstractSpringBootTest {

    @Autowired
    private TimeZoneMapper mapper;

    @Test
    public void dtoShouldBeMappedToEntity() {
        final Long givenId = 255L;
        final String givenName = "name";
        final TimeZone givenDto = TimeZone.builder()
                .id(givenId)
                .name(givenName)
                .build();

        final TimeZoneEntity actual = mapper.toEntity(givenDto);
        final TimeZoneEntity expected = TimeZoneEntity.builder()
                .id(givenId)
                .name(givenName)
                .build();
        checkEquals(expected, actual);
    }

    @Test
    public void entityShouldBeMappedToDto() {
        final Long givenId = 255L;
        final String givenName = "name";
        final TimeZoneEntity givenEntity = TimeZoneEntity.builder()
                .id(givenId)
                .name(givenName)
                .build();

        final TimeZone actual = mapper.toDto(givenEntity);
        final TimeZone expected = TimeZone.builder()
                .id(givenId)
                .name(givenName)
                .build();
        assertEquals(expected, actual);
    }
}
