package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.dto.sensor.calc.SensorCalc;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalc.Meta;
import com.locator.server.notification.bymessage.handlers.NotificationCalcGpsOdometerPeriodControl;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.locator.server.domain.dto.sensor.SensorName.GPS_ODOMETER;
import static com.locator.server.domain.entity.notification.NotificationType.GPS_ODOMETER_PERIOD_CONTROL;

@Component
public class NotificationCalcGpsOdometerPeriodControlCreator
        extends NotificationCalcAbstractOdometerPeriodControlCreator {
    public NotificationCalcGpsOdometerPeriodControlCreator() {
        super(false);
    }

    @Override
    protected Optional<SensorCalc> findSensorCalc(SensorBunchCalculator calculator) {
        return calculator.getSensorByNameOptional(GPS_ODOMETER.label);
    }

    @Override
    protected NotificationCalc createNotificationCalc(Meta meta, SensorCalc sensorCalc, double startValue,
                                                      double finishValue, double period) {
        return new NotificationCalcGpsOdometerPeriodControl(meta, super.continuable, sensorCalc, startValue,
                finishValue, period);
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == GPS_ODOMETER_PERIOD_CONTROL;
    }
}
