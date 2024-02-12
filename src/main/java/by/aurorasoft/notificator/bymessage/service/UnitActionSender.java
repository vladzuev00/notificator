package by.aurorasoft.notificator.bymessage.service;

import com.locator.server.kafka.producer.KafkaProducerUnitActionsLog;
import com.locator.server.notification.bymessage.vo.Not_Unit;
import com.locator.server.notification.bymessage.vo.Not_UnitAction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UnitActionSender {

    private final KafkaProducerUnitActionsLog unitActionsLog;

    public void info(Not_Unit unit, String message) {
        if (isUnitAvailable(unit)) {
            unitActionsLog.send(Not_UnitAction.info(unit.getId(), message));
        }
    }

    public void warn(Not_Unit unit, String message) {
        if (isUnitAvailable(unit)) {
            unitActionsLog.send(Not_UnitAction.warn(unit.getId(), message));
        }
    }

    public void error(Not_Unit unit, String message) {
        if (isUnitAvailable(unit)) {
            unitActionsLog.send(Not_UnitAction.error(unit.getId(), message));
        }
    }

    private boolean isUnitAvailable(Not_Unit unit) {
        return unit != null && unit.getId() != null;
    }
}
