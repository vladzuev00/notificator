package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.dto.sensor.calc.SensorCalc;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.domain.entity.sensor.SensorType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalcGsmStrengthControl;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.GSM_STRENGTH_CONTROL;

@Service
public class NotificationCalcGsmStrengthControlCreator extends NotificationCalcCreator {

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        Optional<SensorCalc> sensor = calculator.getSensorByTypeOptional(SensorType.GSM_STRENGTH);
        if (sensor.isEmpty() || notHas(meta, KEY_MIN)) {
            return Optional.empty();
        }
        double min = getDouble(meta, KEY_MIN);
        return Optional.of(new NotificationCalcGsmStrengthControl(meta, true, sensor.get(), min));
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == GSM_STRENGTH_CONTROL;
    }
}
