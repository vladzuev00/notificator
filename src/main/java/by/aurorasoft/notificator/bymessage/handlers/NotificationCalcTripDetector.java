package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;

public class NotificationCalcTripDetector extends NotificationCalc {

    public NotificationCalcTripDetector(Meta meta, boolean isContinuable) {
        super(meta, isContinuable);
    }

    @Override
    protected boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        return current.isCorrect() && current.getSpeed() > 0;
    }
}
