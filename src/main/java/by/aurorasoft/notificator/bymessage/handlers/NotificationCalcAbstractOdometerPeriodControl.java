package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;
import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationCalcAbstractOdometerPeriodControl extends NotificationCalc {

    Sensor sensor;
    double startValue;
    double finishValue;
    double period;

    public NotificationCalcAbstractOdometerPeriodControl(Meta meta, boolean isContinuable, Sensor sensor, double startValue, double finishValue, double period) {
        super(meta, isContinuable);
        this.sensor = sensor;
        this.startValue = startValue;
        this.finishValue = finishValue;
        this.period = period;
    }

    @Override
    protected boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        double a = calculator.calculate(sensor.getId(), prev);
        double b = calculator.calculate(sensor.getId(), current);
        int kA = (int) ((a - startValue) / period);
        int kB = (int) ((b - startValue) / period);
        return b < finishValue && kB > kA;
    }
}
