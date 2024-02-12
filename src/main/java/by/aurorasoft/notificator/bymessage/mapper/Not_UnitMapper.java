package by.aurorasoft.notificator.bymessage.mapper;

import by.nhorushko.crudgeneric.mapper.ImmutableDtoAbstractMapper;
import com.locator.server.domain.entity.TimeZoneEntity;
import com.locator.server.domain.entity.UserEntity;
import com.locator.server.domain.entity.unit.UnitEntity;
import com.locator.server.notification.bymessage.vo.Not_Unit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public final class Not_UnitMapper extends ImmutableDtoAbstractMapper<UnitEntity, Not_Unit> {
    public Not_UnitMapper(final ModelMapper modelMapper) {
        super(UnitEntity.class, Not_Unit.class, modelMapper);
    }

    @Override
    protected Not_Unit mapDto(final UnitEntity entity) {
        final Long id = entity.getId();
        final String name = entity.getName();

        final UserEntity userEntity = entity.getOwnerUser();
        final TimeZoneEntity timeZoneEntity = userEntity.getTimeZone();
        final String nameTimeZoneEntity = timeZoneEntity.getName();
        final ZoneId zoneId = ZoneId.of(nameTimeZoneEntity);

        return new Not_Unit(id, name, zoneId);
    }
}
