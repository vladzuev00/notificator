package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.dto.sensor.calc.SensorCalc;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.domain.entity.sensor.SensorType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcLowBattery;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.LOW_BATTERY_CONTROL;

@Service
public class NotificationCalcLowBatteryCreator extends NotificationCalcCreator {

    private static final double DEFAULT_VALUE = 15;

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        Optional<SensorCalc> sensor = calculator.getSensorByTypeOptional(SensorType.DEVICE_BATTERY);
        if (sensor.isEmpty()) {
            return Optional.empty();
        }
        double min = getDouble(meta, KEY_MIN, DEFAULT_VALUE);
        return Optional.of(new NotificationCalcLowBattery(meta, true, sensor.get(), min));
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == LOW_BATTERY_CONTROL;
    }
}
