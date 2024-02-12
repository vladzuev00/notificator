package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;
import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;

public class NotificationCalcDowntimeIgnition extends NotificationCalcSensor {

    public NotificationCalcDowntimeIgnition(Meta meta, boolean isContinuable, Sensor sensor) {
        super(meta, isContinuable, sensor);
    }

    @Override
    protected boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        return current.getSpeed() == 0 && calculate(current, calculator) >= 1;
    }
}
