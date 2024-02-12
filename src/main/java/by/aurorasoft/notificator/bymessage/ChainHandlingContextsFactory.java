package by.aurorasoft.notificator.bymessage;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlingchain.ChainHandlingContext;
import com.locator.server.notification.bymessage.service.NotificationSourceCalcService;
import com.locator.server.services.sensor.SensorBunchCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public final class ChainHandlingContextsFactory {
    private final NotificationSourceCalcService sourceCalcService;
    private final SensorBunchCalculatorService bunchCalculatorService;

    public List<ChainHandlingContext> create(final MessageEntity previous, final MessageEntity current) {
        final long unitId = current.getUnitId();
        final SensorBunchCalculator calculator = this.bunchCalculatorService.get(unitId);
        final List<NotificationCalc> notificationCalcs = this.sourceCalcService.list(unitId);
        return notificationCalcs.stream().map(notificationCalc -> ChainHandlingContext.builder()
                        .previous(previous)
                        .current(current)
                        .notificationCalc(notificationCalc)
                        .calculator(calculator)
                        .build())
                .collect(toList());
    }
}
