package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import lombok.Getter;

import java.time.Instant;

@Getter
public class NotificationCalcSatsStrengthControl extends NotificationCalc {

    private final int min;

    public NotificationCalcSatsStrengthControl(Meta meta, boolean isContinuable, int min) {
        super(meta, isContinuable);
        this.min = min;
    }

    @Override
    public boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        return current.getAmountSatellite() <= min;
    }

    @Override
    protected Instant getNotificationStartTime(MessageEntity prev, MessageEntity current) {
        return prev.getDatetime();
    }
}
