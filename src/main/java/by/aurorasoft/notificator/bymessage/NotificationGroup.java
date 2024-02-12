package by.aurorasoft.notificator.bymessage;

import by.aurorasoft.notificator.bymessage.vo.Notification;
import by.aurorasoft.notificator.bymessage.vo.NotificationSource;
import by.aurorasoft.notificator.bymessage.vo.Unit;
import by.aurorasoft.notificator.model.NotificationStatus;
import lombok.Value;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static by.aurorasoft.notificator.model.NotificationStatus.ACTIVE;
import static by.aurorasoft.notificator.model.NotificationStatus.PENDING;
import static java.util.Collections.emptyList;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static java.util.Set.of;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public final class NotificationGroup {
    private static final Set<NotificationStatus> ACTUAL_NOTIFICATION_STATUSES = of(ACTIVE, PENDING);

    private final Map<NotificationStatus, Map<NotificationKey, Notification>> notificationsByKeys;

    public NotificationGroup(final List<Notification> notifications) {
        notificationsByKeys = createHolder(notifications);
    }

    public boolean isContainFor(final Unit unit, final NotificationSource source) {
        return notificationsByKeys.computeIfPresent();

        return ACTUAL_NOTIFICATION_STATUSES.stream().anyMatch(status -> {
            final Map<Long, Notification> correspondingValueMap = this.notificationsByKeys.get(status);
            return correspondingValueMap.containsKey(source);
        });
    }

    public boolean isContain(final NotificationStatus status, final Long sourceId) {
        final Map<Long, Notification> correspondingValueMap = this.notificationsByKeys.get(status);
        return correspondingValueMap != null && correspondingValueMap.containsKey(sourceId);
    }

    public Optional<Notification> find(final NotificationStatus status, final Long sourceId) {
        final Map<Long, Notification> correspondingValueMap = this.notificationsByKeys.get(status);
        return correspondingValueMap != null
                ? ofNullable(correspondingValueMap.get(sourceId))
                : empty();
    }

    public List<Notification> findAll(final NotificationStatus status) {
        final Map<Long, Notification> correspondingValueMap = this.notificationsByKeys.get(status);
        return correspondingValueMap != null
                ? new ArrayList<>(this.notificationsByKeys.get(status).values())
                : emptyList();
    }

    public void add(final Notification notification) {
        if (ACTUAL_NOTIFICATION_STATUSES.contains(notification.getStatus())) {
            this.notificationsByKeys
                    .get(notification.getStatus())
                    .put(notification.getSource().getId(), notification);
        }
    }

    public void remove(final Long sourceId) {
        this.notificationsByKeys.values().forEach(mapValue -> mapValue.remove(sourceId));
    }

    public synchronized void changeStatus(final Notification notification) {
        this.remove(notification.getSource().getId());
        this.add(notification);
    }

    private static Map<Long, Map<Long, Map<NotificationStatus, Notification>>> createHolder(
            final List<Notification> notifications
    ) {
        return notifications.stream()
                .collect(
                        groupingBy(
                                Notification::getUnitId,
                                ConcurrentHashMap::new,
                                groupingBy(
                                        Notification::getSourceId,
                                        //TODO: possibly HashMap
                                        ConcurrentHashMap::new,
                                        toMap(
                                                Notification::getStatus,
                                                identity(),
                                                NotificationGroup::onStatusAndSourceIdDuplication,
                                                //TODO: possibly HashMap
                                                ConcurrentHashMap::new
                                        )
                                )
                        )
                );
    }

    private static Notification onStatusAndSourceIdDuplication(final Notification first, final Notification second) {
        throw new NotificationStatusAndSourceIdShouldBeUnique(
                "There are two notifications with the same status and source's id:\nFirst - %s\nSecond - %s"
                        .formatted(first, second)
        );
    }

    @Value
    private static class NotificationKey {
        Long unitId;
        Long sourceId;
    }

    static final class NotificationStatusAndSourceIdShouldBeUnique extends RuntimeException {

        public NotificationStatusAndSourceIdShouldBeUnique() {

        }

        public NotificationStatusAndSourceIdShouldBeUnique(final String description) {
            super(description);
        }

        public NotificationStatusAndSourceIdShouldBeUnique(final Exception cause) {
            super(cause);
        }

        public NotificationStatusAndSourceIdShouldBeUnique(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
