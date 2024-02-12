package by.aurorasoft.notificator.bymessage.service;

import com.locator.server.notification.bymessage.ActualNotifications;
import com.locator.server.notification.bymessage.vo.Not_Notification;
import com.locator.server.notification.common.service.Not_NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static com.locator.server.notification.bymessage.ActualNotifications.create;

@Component
@RequiredArgsConstructor
public class ActualNotificationsStorage {
    private static final Function<Not_Notification, Long> NOTIFICATION_TO_UNIT_ID_FUNCTION
            = notification -> notification.getUnit().getId();

    private final Map<Long, ActualNotifications> storageMap = new ConcurrentHashMap<>();
    private final Not_NotificationService notificationService;

    public ActualNotifications get(final long unitId) {
        if (this.storageMap.containsKey(unitId)) {
            return this.storageMap.get(unitId);
        }
        final ActualNotifications actualNotifications = create(this.notificationService.getAllActiveAndPending(unitId));
        this.storageMap.put(unitId, actualNotifications);
        return actualNotifications;
    }

    public boolean isContain(final Not_Notification notification) {
        final Long notificationUnitId = NOTIFICATION_TO_UNIT_ID_FUNCTION.apply(notification);
        final ActualNotifications actualNotifications = this.storageMap.get(notificationUnitId);
        return actualNotifications != null && actualNotifications.isContain(notificationUnitId);
    }
}
