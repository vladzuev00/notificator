package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;
import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import lombok.Getter;

@Getter
public class NotificationCalcGsmStrengthControl extends NotificationCalcSensor {

    private final double min;

    public NotificationCalcGsmStrengthControl(Meta meta, boolean isContinuable, Sensor sensor, double min) {
        super(meta, isContinuable, sensor);
        this.min = min;
    }

    @Override
    public boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        double value = calculate(current, calculator);
        return value <= min;
    }
}
