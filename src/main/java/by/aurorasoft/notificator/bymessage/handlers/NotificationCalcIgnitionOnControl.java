package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;
import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;

public class NotificationCalcIgnitionOnControl extends NotificationCalcSensor {

    public NotificationCalcIgnitionOnControl(Meta meta, boolean isContinuable, Sensor sensor) {
        super(meta, isContinuable, sensor);
    }

    @Override
    protected boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        double value = calculate(current, calculator);
        return value >= 1;
    }
}
