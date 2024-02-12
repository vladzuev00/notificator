package by.aurorasoft.notificator.bymessage.handlingchain;

import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.service.Not_NotificationSourceCommandActionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public final class NotificationHookManager {
    private final Not_NotificationSourceCommandActionService commandActionService;

    public NotificationHookManager(final Not_NotificationSourceCommandActionService commandActionService) {
        this.commandActionService = commandActionService;
    }

    public void activateHook(final NotificationCalc notificationCalc, final MessageEntity message) {
        log.debug("Notification activation hook");
        this.commandActionService.handleActivate(notificationCalc, message);
    }

    public void deactivateHook(final NotificationCalc notificationCalc, final MessageEntity message) {
        log.debug("Notification deactivation hook");
        if (notificationCalc.isContinuable()) {
            this.commandActionService.handleDeactivate(notificationCalc, message);
        } else {
            this.commandActionService.handleActivate(notificationCalc, message);
        }
    }
}
