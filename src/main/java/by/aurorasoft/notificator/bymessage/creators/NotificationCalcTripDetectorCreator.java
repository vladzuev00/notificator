package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcTripDetector;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.TRIP_DETECT_CONTROL;

@Service
public class NotificationCalcTripDetectorCreator extends NotificationCalcCreator {

    private static final int DEFAULT_TRIP_DETECTOR_TIME_SECONDS = 300;

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        if (meta.getSource().getPending() == 0) {
            meta.getSource().setPending(DEFAULT_TRIP_DETECTOR_TIME_SECONDS);
        }
        return Optional.of(new NotificationCalcTripDetector(meta, true));
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == TRIP_DETECT_CONTROL;
    }
}
