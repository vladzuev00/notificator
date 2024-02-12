package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.dto.sensor.calc.SensorCalc;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.domain.entity.sensor.SensorType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcFuelLevelChangeControlDecrease;
import com.locator.server.notification.bymessage.handlers.NotificationCalcFuelLevelChangeControlIncrease;
import com.locator.server.notification.bymessage.handlers.NotificationCalcFuelLevelChangeControlIncreaseOrDecrease;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.FUEL_LEVEL_CHANGE_CONTROL;

@Component
public class NotificationCalcFuelLevelChangeControlCreator extends NotificationCalcCreator {

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        if (notHas(meta, KEY_VALUE) || notHas(meta, KEY_ACTION)) {
            return Optional.empty();
        }
        Optional<SensorCalc> optionalSensor = calculator.getSensorByTypeOptional(SensorType.FUEL_TANK_LEVEL);
        if (optionalSensor.isEmpty()) {
            return Optional.empty();
        }

        SensorCalc sensorCalc = optionalSensor.get();
        String action = getString(meta, KEY_ACTION);
        double value = getDouble(meta, KEY_VALUE);

        switch (action) {
            case KEY_INCREASE:
                return Optional.of(new NotificationCalcFuelLevelChangeControlIncrease(meta, true, sensorCalc, value));
            case KEY_DECREASE:
                return Optional.of(new NotificationCalcFuelLevelChangeControlDecrease(meta, true, sensorCalc, value));
            case KEY_INCREASE_OR_DECREASE:
                return Optional.of(new NotificationCalcFuelLevelChangeControlIncreaseOrDecrease(meta, true, sensorCalc, value));
            default:
                return Optional.empty();
        }
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == FUEL_LEVEL_CHANGE_CONTROL;
    }
}
