package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import lombok.Getter;

@Getter
public class NotificationCalcMaxSpeedControl extends NotificationCalc {

    private final int max;

    public NotificationCalcMaxSpeedControl(Meta meta, boolean isContinuable, int max) {
        super(meta, isContinuable);
        this.max = max;
    }

    @Override
    public boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        return current.getSpeed() > max;
    }
}
