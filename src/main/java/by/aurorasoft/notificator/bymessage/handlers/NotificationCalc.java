package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.domain.entity.notification.NotificationStatus;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.geofence.service.JtsGeometryFactory;
import com.locator.server.notification.bymessage.vo.Not_Geofence;
import com.locator.server.notification.bymessage.vo.Not_Notification;
import com.locator.server.notification.bymessage.vo.Not_NotificationSource;
import com.locator.server.notification.bymessage.vo.Not_Unit;
import lombok.Data;
import lombok.Value;

import java.time.Instant;
import java.util.Map;
import java.util.Set;

@Data
public abstract class NotificationCalc {

    protected final Meta meta;
    protected final boolean isContinuable;

    public NotificationCalc(Meta meta, boolean isContinuable) {
        this.meta = meta;
        this.isContinuable = isContinuable;
    }

    public long getUnitId() {
        return getMeta().getUnit().getId();
    }

    public boolean isActivate(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        return withinGeofence(current)
                && isActive(prev, current, calculator)
                && meta.source.getTimeFilter().isFit(current.getDatetime(), meta.getUnit().getOwnerTZ());
    }

    protected abstract boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator);

    public boolean withinGeofence(MessageEntity m) {
        if (meta.getSource().getGeofences().size() == 0) {
            return true;
        }
        if (!m.isValid()) {
            return false;
        }
        for (Not_Geofence geofence : meta.getSource().getGeofences()) {
            if (JtsGeometryFactory.point(m).within(geofence.getGeometry())) {
                return true;
            }
        }
        return false;
    }

    public boolean isPendingable() {
        return meta.source.getPending() > 0;
    }

    public boolean isPendingEnd(Not_Notification notification, MessageEntity message) {
        return message.getDatetime().isAfter(notification.getStartTime().plusSeconds(meta.source.getPending()));
    }

    public Not_Notification createNotification(MessageEntity prev, MessageEntity current) {
        Instant finishTime = null;
        NotificationStatus status = NotificationStatus.ACTIVE;
        if (!isContinuable) {
            finishTime = current.getDatetime();
            status = NotificationStatus.COMPLETED;
        }
        if (isPendingable()) {
            finishTime = null;
            status = NotificationStatus.PENDING;
        }
        return new Not_Notification(
                this.meta.unit,
                this.meta.source,
                getNotificationStartTime(prev, current),
                finishTime,
                status
        );
    }

    protected Instant getNotificationStartTime(MessageEntity prev, MessageEntity current) {
        return current.getDatetime();
    }

    public Long getNotificationSourceId() {
        return meta.source.getId();
    }

    public Set<Not_Geofence> getGeofences() {
        return meta.source.getGeofences();
    }

    @Value
    public static class Meta {
        Not_Unit unit;
        Not_NotificationSource source;

        public NotificationType getType() {
            return source.getType();
        }

        public Map<String, Object> getParams() {
            return source.getParams();
        }
    }
}
