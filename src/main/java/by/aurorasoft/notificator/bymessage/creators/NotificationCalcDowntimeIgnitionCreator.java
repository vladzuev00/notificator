package by.aurorasoft.notificator.bymessage.creators;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.dto.sensor.calc.SensorCalc;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalc.Meta;
import com.locator.server.notification.bymessage.handlers.NotificationCalcDowntimeIgnition;
import com.locator.server.notification.bymessage.vo.Not_NotificationSource;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.locator.server.domain.entity.notification.NotificationType.DOWNTIME_WITH_IGNITION_CONTROL;
import static com.locator.server.domain.entity.sensor.SensorType.IGNITION;

@Service
public class NotificationCalcDowntimeIgnitionCreator extends NotificationCalcCreator {

    private static final int DEFAULT_DOWNTIME_SECONDS = 300;

    @Override
    public Optional<NotificationCalc> create(Meta meta, SensorBunchCalculator calculator) {
        final Optional<SensorCalc> optionalSensorCalc = calculator.getSensorByTypeOptional(IGNITION);
        return optionalSensorCalc.map(sensorCalc ->
        {
            final Not_NotificationSource source = meta.getSource();
            if (source.getPending() == 0) {
                source.setPending(DEFAULT_DOWNTIME_SECONDS);
            }
            return new NotificationCalcDowntimeIgnition(meta, true, sensorCalc);
        });
    }

    @Override
    public boolean canCreate(NotificationType notificationType) {
        return notificationType == DOWNTIME_WITH_IGNITION_CONTROL;
    }
}
