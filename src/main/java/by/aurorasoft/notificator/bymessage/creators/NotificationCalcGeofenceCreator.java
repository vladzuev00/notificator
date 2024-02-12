package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.vo.Not_Geofence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public abstract class NotificationCalcGeofenceCreator extends NotificationCalcCreator {

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        Set<Not_Geofence> geofences = meta.getSource().getGeofences();
        if (geofences.size() == 0) {
            return Optional.empty();
        }
        return create(meta);
    }

    protected abstract Optional<NotificationCalc> create(NotificationCalc.Meta meta);

    public void resetAll(NotificationCalc.Meta meta) {
        meta.getSource().setPending(0);
    }
}
