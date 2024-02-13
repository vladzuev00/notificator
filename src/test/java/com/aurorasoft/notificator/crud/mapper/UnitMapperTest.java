//package com.aurorasoft.notificator.crud.mapper;
//
//import com.aurorasoft.notificator.base.AbstractSpringBootTest;
//import com.aurorasoft.notificator.crud.dto.Unit;
//import com.aurorasoft.notificator.crud.entity.UnitEntity;
//import com.aurorasoft.notificator.model.UnitStatus;
//import com.aurorasoft.notificator.util.UnitEntityUtil;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import static org.junit.Assert.assertEquals;
//
//public final class UnitMapperTest extends AbstractSpringBootTest {
//
//    @Autowired
//    private UnitMapper mapper;
//
//    @Test
//    public void entityShouldBeMappedToDto() {
//        final Long givenId = 255L;
//        final String givenName = "name";
//        final String givenColor = "color";
//        final UnitStatus givenStatus = UnitStatus.ACTIVE;
//        final UnitEntity givenEntity = UnitEntity.builder()
//                .id(givenId)
//                .name(givenName)
//                .color(givenColor)
//                .status(givenStatus)
//                .build();
//
//        final Unit actual = mapper.toDto(givenEntity);
//        final Unit expected = Unit.builder()
//                .id(givenId)
//                .name(givenName)
//                .color(givenColor)
//                .status(givenStatus)
//                .build();
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    public void dtoShouldBeMappedToEntity() {
//        final Long givenId = 255L;
//        final String givenName = "name";
//        final String givenColor = "color";
//        final UnitStatus givenStatus = UnitStatus.ACTIVE;
//        final Unit givenDto = Unit.builder()
//                .id(givenId)
//                .name(givenName)
//                .color(givenColor)
//                .status(givenStatus)
//                .build();
//
//        final UnitEntity actual = mapper.toEntity(givenDto);
//        final UnitEntity expected = UnitEntity.builder()
//                .id(givenId)
//                .name(givenName)
//                .color(givenColor)
//                .status(givenStatus)
//                .build();
//        UnitEntityUtil.checkEquals(expected, actual);
//    }
//}
