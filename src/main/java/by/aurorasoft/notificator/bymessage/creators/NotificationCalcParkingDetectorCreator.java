package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcParkingDetector;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.PARKING_DETECT_CONTROL;

@Service
public class NotificationCalcParkingDetectorCreator extends NotificationCalcCreator {

    private static final int DEFAULT_PARKING_DETECTOR_TIME_SECONDS = 300;

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        if (meta.getSource().getPending() == 0) {
            meta.getSource().setPending(DEFAULT_PARKING_DETECTOR_TIME_SECONDS);
        }
        return Optional.of(new NotificationCalcParkingDetector(meta, true));
    }

    @Override
    public boolean canCreate(final NotificationType notificationType) {
        return notificationType == PARKING_DETECT_CONTROL;
    }
}
