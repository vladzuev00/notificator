package by.aurorasoft.notificator.crud.service;

import by.aurorasoft.notificator.crud.dto.Unit;
import by.aurorasoft.notificator.crud.entity.UnitEntity;
import by.aurorasoft.notificator.crud.mapper.UnitMapper;
import by.aurorasoft.notificator.crud.repository.UnitRepository;
import by.nhorushko.crudgeneric.v2.service.AbsServiceCRUD;
import org.springframework.stereotype.Service;

@Service
public class UnitService extends AbsServiceCRUD<Long, UnitEntity, Unit, UnitRepository> {

    public UnitService(final UnitMapper mapper, final UnitRepository repository) {
        super(mapper, repository);
    }

}
