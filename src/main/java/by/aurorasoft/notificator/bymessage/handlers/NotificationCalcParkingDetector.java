package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;

public class NotificationCalcParkingDetector extends NotificationCalc {

    public NotificationCalcParkingDetector(Meta meta, boolean isContinuable) {
        super(meta, isContinuable);
    }

    @Override
    protected boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        return current.isValid() && current.getSpeed() == 0;
    }
}
