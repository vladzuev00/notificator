package by.aurorasoft.notificator.bymessage.handlingchain;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ChainHandlingContext {
    MessageEntity previous;
    MessageEntity current;
    NotificationCalc notificationCalc;
    SensorBunchCalculator calculator;
}
