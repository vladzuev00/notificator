package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;
import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class NotificationCalcSensorControlWithinRange extends NotificationCalcSensor {

    double min;
    double max;

    public NotificationCalcSensorControlWithinRange(Meta meta, boolean isContinuable,
                                                    Sensor sensor, double min, double max) {
        super(meta, isContinuable, sensor);
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        double v = calculate(current, calculator);
        return v >= min && v <= max;
    }
}
