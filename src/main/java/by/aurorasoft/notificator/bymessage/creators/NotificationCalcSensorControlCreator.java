package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.dto.sensor.calc.SensorCalc;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcSensorControlOutOfRange;
import com.locator.server.notification.bymessage.handlers.NotificationCalcSensorControlWithinRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.SENSOR_RANGE_CONTROL;

@Slf4j
@Service
public class NotificationCalcSensorControlCreator extends NotificationCalcCreator {

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        if ((notHas(meta, KEY_MIN) && notHas(meta, KEY_MAX)) || notHas(meta, KEY_SENSOR_NAME) || notHas(meta, KEY_ACTION)) {
            return Optional.empty();
        }
        Optional<SensorCalc> optionalSensor = calculator.getSensorByNameOptional(getString(meta, KEY_SENSOR_NAME));
        if (optionalSensor.isEmpty()) {
            return Optional.empty();
        }

        double min = getDouble(meta, KEY_MIN, Double.MIN_VALUE);
        double max = getDouble(meta, KEY_MAX, Double.MAX_VALUE);
        String action = getString(meta, KEY_ACTION);
        SensorCalc sensor = optionalSensor.get();

        switch (action) {
            case ACTION_WITHIN:
                return Optional.of(new NotificationCalcSensorControlWithinRange(meta, true, sensor, min, max));
            case ACTION_OUT_OF:
                return Optional.of(new NotificationCalcSensorControlOutOfRange(meta, true, sensor, min, max));
            default:
                return Optional.empty();
        }
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == SENSOR_RANGE_CONTROL;
    }
}
