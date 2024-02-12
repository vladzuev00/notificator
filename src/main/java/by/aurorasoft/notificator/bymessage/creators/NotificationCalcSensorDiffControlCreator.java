package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.dto.sensor.calc.SensorCalc;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.SENSOR_DIFF_CONTROL;

@Slf4j
@Service
public class NotificationCalcSensorDiffControlCreator extends NotificationCalcCreator {

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        if ((notHas(meta, KEY_VALUE) || notHas(meta, KEY_SENSOR_NAME) || notHas(meta, KEY_ACTION))) {
            return Optional.empty();
        }
        Optional<SensorCalc> optionalSensor = calculator.getSensorByNameOptional(getString(meta, KEY_SENSOR_NAME));
        if (optionalSensor.isEmpty()) {
            return Optional.empty();
        }

        double expectedDiff = getDouble(meta, KEY_VALUE);
        String action = getString(meta, KEY_ACTION);
        SensorCalc sensor = optionalSensor.get();

        switch (action) {
            case ACTION_MORE:
                return Optional.of(new NotificationCalcSensorDiffMoreControl(meta, true, sensor, expectedDiff));
            case ACTION_MORE_OR_EQUALS:
                return Optional.of(new NotificationCalcSensorDiffMoreOrEqualsControl(meta, true, sensor, expectedDiff));
            case ACTION_LESS:
                return Optional.of(new NotificationCalcSensorDiffLessControl(meta, true, sensor, expectedDiff));
            case ACTION_LESS_OR_EQUALS:
                return Optional.of(new NotificationCalcSensorDiffLessOrEqualsControl(meta, true, sensor, expectedDiff));
            case ACTION_EQUALS:
                return Optional.of(new NotificationCalcSensorDiffEqualsControl(meta, true, sensor, expectedDiff));
            default:
                return Optional.empty();
        }
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == SENSOR_DIFF_CONTROL;
    }
}
