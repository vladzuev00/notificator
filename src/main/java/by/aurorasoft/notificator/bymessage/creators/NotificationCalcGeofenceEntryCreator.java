package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcGeofenceEntry;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.EXIT_GEOFENCE_CONTROL;

@Service
public class NotificationCalcGeofenceEntryCreator extends NotificationCalcGeofenceCreator {

    @Override
    protected Optional<NotificationCalc> create(NotificationCalc.Meta meta) {
        resetAll(meta);
        return Optional.of(new NotificationCalcGeofenceEntry(meta, false));
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == EXIT_GEOFENCE_CONTROL;
    }
}
