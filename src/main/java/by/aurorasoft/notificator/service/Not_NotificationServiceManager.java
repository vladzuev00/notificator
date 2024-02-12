package by.aurorasoft.notificator.service;

import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.notification.bymessage.ActualNotifications;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.service.ActualNotificationsStorage;
import com.locator.server.notification.bymessage.vo.Not_Notification;
import com.locator.server.notification.bymessage.vo.Not_NotificationSource;
import com.locator.server.notification.bymessage.vo.Not_Unit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationStatus.ACTIVE;
import static com.locator.server.domain.entity.notification.NotificationStatus.PENDING;

@Service
@RequiredArgsConstructor
@Slf4j
public class Not_NotificationServiceManager {
    private final Not_NotificationService service;
    private final ActualNotificationsStorage storage;

    public ActualNotifications getActualNotifications(long unitId) {
        return this.storage.get(unitId);
    }

    public boolean isNew(NotificationCalc notificationCalc) {
        return !getActualNotifications(notificationCalc).isContain(notificationCalc.getNotificationSourceId());
    }

    public Optional<Not_Notification> getPending(final NotificationCalc notificationCalc) {
        final ActualNotifications actualNotifications = this.getActualNotifications(notificationCalc);
        final long notificationSourceId = notificationCalc.getNotificationSourceId();
        return actualNotifications.find(PENDING, notificationSourceId);
    }

    public Optional<Not_Notification> getActive(final NotificationCalc notificationCalc) {
        final ActualNotifications actualNotifications = this.getActualNotifications(notificationCalc);
        final long notificationSourceId = notificationCalc.getNotificationSourceId();
        return actualNotifications.find(ACTIVE, notificationSourceId);
    }

    public Not_Notification save(Not_Notification notification) {
        if (!this.storage.isContain(notification)) {
            final Not_Notification saved = this.service.save(notification);
            getActualNotifications(notification.getUnit().getId()).add(notification);
            return saved;
        }
        return notification;
    }

    private void updateToCompleted(Not_Notification notification, Instant finishTime) {
        final long notificationSourceId = notification.getSource().getId();
        log.debug("update to completed notification: " + notificationSourceId);
        this.service.updateToCompleted(notification, finishTime);
        final ActualNotifications actualNotifications = this.getActualNotifications(notification.getUnit().getId());
        actualNotifications.remove(notificationSourceId);
    }

    public void updateToCompleted(final Not_Notification notification, final MessageEntity message) {
        this.updateToCompleted(notification, message.getDatetime());
    }

    public void updateToCompleted(Not_Unit unit, Not_NotificationSource source, Instant finishTime) {
        this.getActualNotifications(unit.getId()).find(ACTIVE, source.getId())
                .ifPresent(notification -> this.updateToCompleted(notification, finishTime));
    }

    public void updateToActive(Not_Notification n) {
        log.debug("update from pending to active notification: " + n.getSource().getId());
        Not_Notification notification = service.updateToActive(n);
        getActualNotifications(notification.getUnit().getId()).changeStatus(notification);
    }

    public void updateToCancelled(Not_Notification n, MessageEntity message) {
        log.debug("update to cancelled notification: " + n.getSource().getId());
        Not_Notification notification = service.updateToCancelled(n, message.getDatetime());
        getActualNotifications(notification.getUnit().getId()).remove(notification.getSource().getId());
    }

    private ActualNotifications getActualNotifications(NotificationCalc notificationCalc) {
        return this.storage.get(notificationCalc.getUnitId());
    }
}
