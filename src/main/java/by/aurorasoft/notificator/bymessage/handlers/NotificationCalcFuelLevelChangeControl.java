package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;
import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.domain.entity.notification.NotificationStatus;
import com.locator.server.notification.bymessage.vo.Not_Notification;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
public abstract class NotificationCalcFuelLevelChangeControl extends NotificationCalc {
    Sensor sensor;
    double value;

    public NotificationCalcFuelLevelChangeControl(Meta meta, boolean isContinuable, Sensor sensor, double value) {
        super(meta, isContinuable);
        this.sensor = sensor;
        this.value = value;
    }

    @Override
    public Not_Notification createNotification(MessageEntity prev, MessageEntity current) {

            NotificationStatus status = NotificationStatus.ACTIVE;

            return new Not_Notification(
                    meta.getUnit(),
                    meta.getSource(),
                    prev.getDatetime(),
                    null,
                    status
            );
    }

    @Override
    protected boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        if (prev.getSpeed() != 0 || current.getSpeed() != 0) {
            return false;
        }
        double a = calculator.calculate(sensor.getId(), prev);
        double b = calculator.calculate(sensor.getId(), current);
        return compareLevels(a, b);
    }

    protected abstract boolean compareLevels(double a, double b);
}
