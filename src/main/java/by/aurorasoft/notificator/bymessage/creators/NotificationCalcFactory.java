package by.aurorasoft.notificator.bymessage.creators;

import by.nhorushko.logback.appender.TelegramAlertAppender;
import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.handlers.NotificationCalc.Meta;
import com.locator.server.notification.bymessage.service.Not_UnitService;
import com.locator.server.notification.bymessage.service.UnitActionSender;
import com.locator.server.notification.bymessage.vo.Not_NotificationSource;
import com.locator.server.notification.bymessage.vo.Not_Unit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationCalcFactory {

    private final Not_UnitService not_unitService;
    private final UnitActionSender unitActionSender;
    private final List<NotificationCalcCreator> creators;

    public List<NotificationCalc> create(long unitId, Collection<Not_NotificationSource> sources,
                                         SensorBunchCalculator calculator) {
        final Not_Unit unit = this.not_unitService.getById(unitId);
        List<NotificationCalc> result = new LinkedList<>();
        for (Not_NotificationSource source : sources) {
            Meta meta = new Meta(unit, source);
            Optional<NotificationCalc> notification = create(meta, calculator);
            notification.ifPresentOrElse(
                    result::add,
                    () -> {
                        log.warn(TelegramAlertAppender.ALERT_MARKER, "Error while creating notification from source: {}, unit: {}", source.getId(), unit);
                        unitActionSender.error(unit, String.format("Error while creating notification from source (id: %s name: %s), unit: %s", source.getId(), source.getName(), unit));
                    });
        }
        return result;
    }

    public Optional<NotificationCalc> create(Meta meta, SensorBunchCalculator calculator) {
        final NotificationType type = meta.getType();
        return this.creators
                .stream()
                .filter(creator -> creator.canCreate(type))
                .findAny()
                .flatMap(creator -> creator.create(meta, calculator));
    }
}
