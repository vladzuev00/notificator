package com.aurorasoft.notificator.service;

import com.aurorasoft.notificator.crud.dto.Notification;
import com.aurorasoft.notificator.model.NotificationStatus;
import lombok.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static com.aurorasoft.notificator.model.NotificationStatus.ACTIVE;
import static com.aurorasoft.notificator.model.NotificationStatus.PENDING;
import static java.util.Set.of;

public class NotificationManager {
    private static final Set<NotificationStatus> ACTUAL_NOTIFICATION_STATUSES = of(ACTIVE, PENDING);

    private final Map<NotificationKey, Notification> actualNotificationsByKeys = new ConcurrentHashMap<>(;)

    public boolean isNew(final NotificationCalc )


    @Value
    private static class NotificationKey {
        Long unitId;
        NotificationStatus status;
        Long sourceId;
    }
}
