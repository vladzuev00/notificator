package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.dto.sensor.calc.SensorCalc;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalc.Meta;
import com.locator.server.notification.bymessage.handlers.NotificationCalcOdometerPeriodControl;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.ODOMETER_PERIOD_CONTROL;
import static com.locator.server.domain.entity.sensor.SensorType.ODOMETER;

@Component
public class NotificationCalcOdometerPeriodControlCreator
        extends NotificationCalcAbstractOdometerPeriodControlCreator {
    public NotificationCalcOdometerPeriodControlCreator() {
        super(false);
    }

    @Override
    protected Optional<SensorCalc> findSensorCalc(SensorBunchCalculator calculator) {
        return calculator.getSensorByTypeOptional(ODOMETER);
    }

    @Override
    protected NotificationCalc createNotificationCalc(Meta meta, SensorCalc sensorCalc, double startValue,
                                                      double finishValue, double period) {
        return new NotificationCalcOdometerPeriodControl(meta, super.continuable, sensorCalc, startValue, finishValue,
                period);
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == ODOMETER_PERIOD_CONTROL;
    }
}
