package by.aurorasoft.notificator.service;

import by.nhorushko.crudgeneric.mapper.Mapper;
import com.locator.server.domain.entity.notification.NotificationSourceEntity;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.vo.Not_NotificationSource;
import com.locator.server.repositories.NotificationSourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class Not_NotificationSourceService {
    private final NotificationSourceRepository repository;
    private final Mapper<NotificationSourceEntity, Not_NotificationSource> mapper;

    public Not_NotificationSourceService(final NotificationSourceRepository repository,
                                         final Mapper<NotificationSourceEntity, Not_NotificationSource> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Not_NotificationSource> list(final long unitId) {
        final List<NotificationSourceEntity> notificationSourceEntities = this.repository
                .findAllByUnitId(unitId);
        return this.mapper.toDto(notificationSourceEntities);
    }

    public List<Not_NotificationSource> listEnabled(final long unitId) {
        final List<NotificationSourceEntity> notificationSourceEntities = this.repository
                .findAllEnabledByUnitId(unitId);
        return this.mapper.toDto(notificationSourceEntities);
    }

    public List<Not_NotificationSource> list(final NotificationType type) {
        final List<NotificationSourceEntity> sourceEntities = this.repository.findAllByType(type);
        return this.mapper.toDto(sourceEntities);
    }
}
