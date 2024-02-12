package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationCalcGpsOdometerPeriodControl extends NotificationCalcAbstractOdometerPeriodControl {

    public NotificationCalcGpsOdometerPeriodControl(Meta meta, boolean isContinuable, Sensor sensor, double startValue, double finishValue, double period) {
        super(meta, isContinuable, sensor, startValue, finishValue, period);
    }
}
