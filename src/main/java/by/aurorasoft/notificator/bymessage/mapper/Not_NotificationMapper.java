package by.aurorasoft.notificator.bymessage.mapper;

import by.nhorushko.crudgeneric.mapper.AbstractMapper;
import by.nhorushko.crudgeneric.mapper.ImmutableDtoAbstractMapper;
import com.locator.server.domain.entity.notification.NotificationEntity;
import com.locator.server.domain.entity.notification.NotificationSourceEntity;
import com.locator.server.domain.entity.unit.UnitEntity;
import com.locator.server.notification.bymessage.vo.Not_Notification;
import com.locator.server.notification.bymessage.vo.Not_NotificationSource;
import com.locator.server.notification.bymessage.vo.Not_Unit;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.time.Instant;

@Component
public class Not_NotificationMapper
        extends ImmutableDtoAbstractMapper<NotificationEntity, Not_Notification> {

    private final AbstractMapper<UnitEntity, Not_Unit> unitEntityMapper;
    private final AbstractMapper<NotificationSourceEntity, Not_NotificationSource> notificationSourceEntityMapper;

    private final EntityManager entityManager;

    public Not_NotificationMapper(ModelMapper modelMapper,
                                  AbstractMapper<UnitEntity, Not_Unit> unitEntityMapper,
                                  AbstractMapper<NotificationSourceEntity, Not_NotificationSource> notificationSourceEntityMapper,
                                  EntityManager entityManager) {
        super(NotificationEntity.class, Not_Notification.class, modelMapper);
        this.unitEntityMapper = unitEntityMapper;
        this.notificationSourceEntityMapper = notificationSourceEntityMapper;
        this.entityManager = entityManager;
    }

    @Override
    protected Not_Notification mapDto(final NotificationEntity e) {
        return new Not_Notification(
                e.getId(),
                this.unitEntityMapper.toDto(e.getUnit()),
                this.notificationSourceEntityMapper.toDto(e.getSource()),
                e.getStartTime(),
                e.getFinishTime(),
                e.getStatus()
        );
    }

    @Override
    protected NotificationEntity mapEntity(Not_Notification dto) {
        return new NotificationEntity(
                dto.getId(),
                this.entityManager.getReference(UnitEntity.class, dto.getUnit().getId()),
                this.entityManager.getReference(NotificationSourceEntity.class, dto.getSource().getId()),
                dto.getStartTime(),
                dto.getFinishTime(),
                dto.getStatus(),
                false,
                Instant.now()
        );
    }
}

