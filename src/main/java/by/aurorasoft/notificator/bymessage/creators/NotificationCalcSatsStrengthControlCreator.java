package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcSatsStrengthControl;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.SATS_STRENGTH_CONTROL;

@Service
public class NotificationCalcSatsStrengthControlCreator extends NotificationCalcCreator {

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        Object o = get(meta, KEY_MIN);
        if (o == null) {
            return Optional.empty();
        }
        return Optional.of(new NotificationCalcSatsStrengthControl(meta, true, Integer.parseInt(o.toString())));
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == SATS_STRENGTH_CONTROL;
    }
}
