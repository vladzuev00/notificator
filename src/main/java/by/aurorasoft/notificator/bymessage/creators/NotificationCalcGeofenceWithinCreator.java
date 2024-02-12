package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcGeofenceWithin;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.STAYING_GEOFENCE_CONTROL;

@Service
public class NotificationCalcGeofenceWithinCreator extends NotificationCalcGeofenceCreator {

    @Override
    protected Optional<NotificationCalc> create(NotificationCalc.Meta meta) {
        return Optional.of(new NotificationCalcGeofenceWithin(meta, true));
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == STAYING_GEOFENCE_CONTROL;
    }
}
