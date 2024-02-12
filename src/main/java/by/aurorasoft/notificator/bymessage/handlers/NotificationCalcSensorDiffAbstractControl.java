package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;
import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class NotificationCalcSensorDiffAbstractControl extends NotificationCalcSensor {
    double expectedDiff;

    public NotificationCalcSensorDiffAbstractControl(Meta meta, boolean isContinuable, Sensor sensor, double expectedDiff) {
        super(meta, isContinuable, sensor);
        this.expectedDiff = expectedDiff;
    }

    @Override
    public boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        double prevValue = calculator.calculate(sensor.getId(), prev);
        double nextValue = calculator.calculate(sensor.getId(), current);
        double actualDiff = nextValue - prevValue;
        return diffAction(actualDiff, expectedDiff);
    }

    @Override
    protected Instant getNotificationStartTime(MessageEntity prev, MessageEntity current) {
        return prev.getDatetime();
    }

    protected abstract boolean diffAction(double actualDiff, double expectedDiff);
}
