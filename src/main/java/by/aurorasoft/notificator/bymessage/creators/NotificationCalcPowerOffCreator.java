package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.dto.sensor.calc.SensorCalc;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.domain.entity.sensor.SensorType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcPowerOff;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.POWER_OFF_CONTROL;

@Service
public class NotificationCalcPowerOffCreator extends NotificationCalcCreator {

    private static final double DEFAULT_VALUE = 1;

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        Optional<SensorCalc> sensor = calculator.getSensorByTypeOptional(SensorType.ONBOARD_VOLTAGE);
        if (sensor.isEmpty()) {
            return Optional.empty();
        }
        double minVoltage = getDouble(meta, KEY_MIN, DEFAULT_VALUE);
        return Optional.of(new NotificationCalcPowerOff(meta, true, sensor.get(), minVoltage));
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == POWER_OFF_CONTROL;
    }
}
