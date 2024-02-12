package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.dto.sensor.calc.SensorCalc;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcSensorControlNotValid;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.SENSOR_NOT_VALID_CONTROL;

@Service
public class NotificationCalcSensorNotValidControlCreator extends NotificationCalcCreator {

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        if (notHas(meta, KEY_SENSOR_NAME)) {
            return Optional.empty();
        }
        Optional<SensorCalc> optionalSensor = calculator.getSensorByNameOptional(getString(meta, KEY_SENSOR_NAME));
        if (optionalSensor.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new NotificationCalcSensorControlNotValid(meta, true, optionalSensor.get()));
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == SENSOR_NOT_VALID_CONTROL;
    }
}
