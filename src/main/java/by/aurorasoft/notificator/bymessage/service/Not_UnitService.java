package by.aurorasoft.notificator.bymessage.service;

import by.nhorushko.crudgeneric.exception.AppNotFoundException;
import by.nhorushko.crudgeneric.mapper.AbstractMapper;
import com.locator.server.domain.entity.unit.UnitEntity;
import com.locator.server.notification.bymessage.vo.Not_Unit;
import com.locator.server.repositories.UnitRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static java.lang.String.format;


@Slf4j
@Service
@AllArgsConstructor
public final class Not_UnitService {

    private final UnitRepository unitRepository;
    private final AbstractMapper<UnitEntity, Not_Unit> unitEntityMapper;

    public Not_Unit getById(final Long id) {
        return unitRepository.findById(id).map(this.unitEntityMapper::toDto).orElseThrow(()
                -> new AppNotFoundException(format("Unit: %d was not found", id)));
    }
}
