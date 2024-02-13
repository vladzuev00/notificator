package com.aurorasoft.notificator.crud.service;

import com.aurorasoft.notificator.crud.dto.Unit;
import com.aurorasoft.notificator.crud.entity.UnitEntity;
import com.aurorasoft.notificator.crud.mapper.UnitMapper;
import com.aurorasoft.notificator.crud.repository.UnitRepository;
import by.nhorushko.crudgeneric.v2.service.AbsServiceCRUD;
import org.springframework.stereotype.Service;

@Service
public class UnitService extends AbsServiceCRUD<Long, UnitEntity, Unit, UnitRepository> {

    public UnitService(final UnitMapper mapper, final UnitRepository repository) {
        super(mapper, repository);
    }

}
