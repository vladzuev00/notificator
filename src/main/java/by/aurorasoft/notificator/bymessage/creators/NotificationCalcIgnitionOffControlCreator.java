package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.dto.sensor.calc.SensorCalc;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.domain.entity.sensor.SensorType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcIgnitionOffControl;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.IGNITION_OFF_CONTROL;

@Service
public class NotificationCalcIgnitionOffControlCreator extends NotificationCalcCreator {
    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        Optional<SensorCalc> sensor = calculator.getSensorByTypeOptional(SensorType.IGNITION);
        if (sensor.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new NotificationCalcIgnitionOffControl(meta, true, sensor.get()));
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == IGNITION_OFF_CONTROL;
    }
}
