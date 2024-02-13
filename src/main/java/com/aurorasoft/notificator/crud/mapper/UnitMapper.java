package com.aurorasoft.notificator.crud.mapper;

import com.aurorasoft.notificator.crud.dto.Unit;
import com.aurorasoft.notificator.crud.entity.UnitEntity;
import by.nhorushko.crudgeneric.v2.mapper.AbsMapperEntityDto;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public final class UnitMapper extends AbsMapperEntityDto<UnitEntity, Unit> {

    public UnitMapper(final ModelMapper modelMapper, final EntityManager entityManager) {
        super(modelMapper, entityManager, UnitEntity.class, Unit.class);
    }

    @Override
    protected Unit create(final UnitEntity entity) {
//        return new Unit(
//                entity.getId(),
//                entity.getName(),
//                entity.getColor(),
//                entity.getStatus()
//        );
        return null;
    }
}
