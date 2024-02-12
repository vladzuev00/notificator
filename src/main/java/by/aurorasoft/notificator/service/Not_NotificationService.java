package by.aurorasoft.notificator.service;


import by.aurorasoft.notificator.bymessage.service.KafkaProducerNot_NotificationBacklog;
import com.locator.server.domain.entity.notification.NotificationEntity;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.mapper.Not_NotificationMapper;
import com.locator.server.notification.bymessage.service.KafkaProducerNot_NotificationBacklog;
import com.locator.server.notification.bymessage.vo.Not_Notification;
import com.locator.server.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationStatus.*;

@Slf4j
@Service
@AllArgsConstructor
public class Not_NotificationService {

    private final NotificationRepository repository;
    private final KafkaProducerNot_NotificationBacklog kafkaService;
    private final Not_NotificationMapper mapper;

    public List<Not_Notification> getAllActiveAndPending(final Long unitId) {
        final List<NotificationEntity> activeAndPendingNotificationEntities = this.repository
                .findAllActiveAndPending(unitId);
        return this.mapper.toDto(activeAndPendingNotificationEntities);
    }

    public List<Not_Notification> getAllActive(final NotificationType type) {
        final List<NotificationEntity> activeNotificationEntities = this.repository.findAllActive(type);
        return this.mapper.toDto(activeNotificationEntities);
    }

    public Optional<Not_Notification> getById(Long id) {
        final Optional<NotificationEntity> optionalNotificationEntity = this.repository.findById(id);
        return optionalNotificationEntity.map(this.mapper::toDto);
    }

    public Not_Notification save(Not_Notification notification) {
        NotificationEntity entity = this.mapper.toEntity(notification);
        entity = this.repository.save(entity);
        final Not_Notification dtoNotification = this.mapper.toDto(entity);
        sendNotificationToKafka(dtoNotification);
        return dtoNotification;
    }

    public Not_Notification updateToCompleted(Not_Notification notification, Instant finishTime) {
        final NotificationEntity notificationEntity = this.mapper.toEntity(notification);
        notificationEntity.setStatus(COMPLETED);
        notificationEntity.setFinishTime(finishTime);
        final NotificationEntity savedNotificationEntity = this.repository.save(notificationEntity);
        return this.mapper.toDto(savedNotificationEntity);
    }

    public Not_Notification updateToActive(final Not_Notification notificationDto) {
        final NotificationEntity notificationEntity = this.mapper.toEntity(notificationDto);
        notificationEntity.setStatus(ACTIVE);
        final NotificationEntity savedNotificationEntity = this.repository.save(notificationEntity);
        return this.mapper.toDto(savedNotificationEntity);
    }

    public Not_Notification updateToCancelled(final Not_Notification notificationDto, final Instant finishTime) {
        final NotificationEntity notificationEntityToSave = this.mapper.toEntity(notificationDto);
        notificationEntityToSave.setStatus(CANCELLED);
        notificationEntityToSave.setFinishTime(finishTime);
        final NotificationEntity savedNotificationEntity = this.repository.save(notificationEntityToSave);
        return this.mapper.toDto(savedNotificationEntity);
    }

    private void sendNotificationToKafka(final Not_Notification notificationDto) {
        if (notificationDto.getStatus() == ACTIVE
                || notificationDto.getStatus() == COMPLETED) {
            log.debug("send Notification to kafka: {}", notificationDto);
            this.kafkaService.send(notificationDto);
        }
    }
}
