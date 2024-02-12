package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcMaxSpeedControl;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.MAX_SPEED_CONTROL;

@Service
public class NotificationCalcMaxSpeedControlCreator extends NotificationCalcCreator {

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        Object o = get(meta, KEY_MAX);
        if (o == null) {
            return Optional.empty();
        }
        return Optional.of(new NotificationCalcMaxSpeedControl(meta, true, Integer.parseInt(o.toString())));
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == MAX_SPEED_CONTROL;
    }
}
