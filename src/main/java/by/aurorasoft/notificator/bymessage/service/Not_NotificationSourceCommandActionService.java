package by.aurorasoft.notificator.bymessage.service;

import com.locator.server.domain.dto.command.CommandStatus;
import com.locator.server.domain.dto.command.DeviceCommand;
import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.domain.entity.command.CommandType;
import com.locator.server.domain.entity.command.ProviderType;
import com.locator.server.kafka.producer.KafkaProducerCommandToSendLog;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.vo.Not_NotificationSourceActionCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@AllArgsConstructor
public class Not_NotificationSourceCommandActionService {

    private static final Instant MIN_VALID_TIME = Instant.parse("2010-01-01T00:00:00Z");
    private final KafkaProducerCommandToSendLog commandToSendLog;

    public void handleActivate(NotificationCalc notificationCalc, MessageEntity message) {
        Not_NotificationSourceActionCommand actionCommand = notificationCalc.getMeta().getSource().getActionCommand();
        if (actionCommand == null || notHasAction(actionCommand.getActivate())) {
            return;
        }
        sendCommand(notificationCalc.getUnitId(), actionCommand.getActivate(), message);
        log.debug("unitId: {}, send ACTIVATE notificationActionCommand: {}", notificationCalc.getUnitId(), actionCommand);
    }

    public void handleDeactivate(NotificationCalc notificationCalc, MessageEntity message) {
        Not_NotificationSourceActionCommand actionCommand = notificationCalc.getMeta().getSource().getActionCommand();
        if (actionCommand == null || notHasAction(actionCommand.getDeactivate())) {
            return;
        }
        sendCommand(notificationCalc.getUnitId(), actionCommand.getDeactivate(), message);
        log.debug("unitId: {}, send DEACTIVATE notificationActionCommand: {}", notificationCalc.getUnitId(), actionCommand);
    }

    private boolean notHasAction(Not_NotificationSourceActionCommand.Action action) {
        return action == null || action.getText() == null || action.getText().trim().length() == 0;
    }

    private void sendCommand(long unitId, Not_NotificationSourceActionCommand.Action action, MessageEntity message) {
        if (message.getDatetime().isBefore(MIN_VALID_TIME)) {
            commandToSendLog.send(command(action, Instant.now()), unitId);
        } else {
            commandToSendLog.send(command(action, message.getDatetime()), unitId);
        }
    }

    private DeviceCommand command(final Not_NotificationSourceActionCommand.Action action, final Instant createdTime) {
        return new DeviceCommand(0L, CommandStatus.SENDING, null, createdTime, CommandType.TCP_COMMAND,
                ProviderType.NOTIFICATION_ACTION, action.getText(), action.getTimeout());
    }
}

