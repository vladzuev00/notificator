package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcGeofenceExit;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.EXIT_GEOFENCE_CONTROL;

@Service
public class NotificationCalcGeofenceExitCreator extends NotificationCalcGeofenceCreator {

    @Override
    protected Optional<NotificationCalc> create(NotificationCalc.Meta meta) {
        return Optional.of(new NotificationCalcGeofenceExit(meta, false));
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == EXIT_GEOFENCE_CONTROL;
    }
}
