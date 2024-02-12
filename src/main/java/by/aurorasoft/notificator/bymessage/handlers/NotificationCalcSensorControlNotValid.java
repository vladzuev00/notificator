package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;
import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import lombok.Getter;

@Getter
public class NotificationCalcSensorControlNotValid extends NotificationCalcSensor {

    public NotificationCalcSensorControlNotValid(Meta meta, boolean isContinuable, Sensor sensor) {
        super(meta, isContinuable, sensor);
    }

    @Override
    public boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        double v = calculate(current, calculator);
        return Double.isNaN(v);
    }
}
