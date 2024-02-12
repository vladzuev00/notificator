package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;
import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public abstract class NotificationCalcSensor extends NotificationCalc {

    protected final Sensor sensor;

    public NotificationCalcSensor(Meta meta, boolean isContinuable, Sensor sensor) {
        super(meta, isContinuable);
        this.sensor = sensor;
    }

    protected double calculate(MessageEntity message, SensorBunchCalculator calculator) {
        if (sensor == null) {
            return Double.NaN;
        }
        return calculator.calculate(sensor.getId(), message);
    }
}
