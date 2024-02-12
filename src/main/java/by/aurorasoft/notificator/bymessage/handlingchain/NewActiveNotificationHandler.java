package by.aurorasoft.notificator.bymessage.handlingchain;

import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.vo.Not_Notification;
import com.locator.server.notification.common.service.Not_NotificationServiceManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.locator.server.domain.entity.notification.NotificationStatus.ACTIVE;

@Component
@Slf4j
public final class NewActiveNotificationHandler extends NotificationHandler {

    public NewActiveNotificationHandler(final FinisherNotificationHandler nextHandler,
                                        final Not_NotificationServiceManager serviceManager,
                                        final NotificationHookManager hookManager) {
        super(nextHandler, serviceManager, hookManager);
    }

    @Override
    protected boolean isHandleable(final ChainHandlingContext context) {
        final NotificationCalc notificationCalc = context.getNotificationCalc();
        return super.notificationServiceManager.isNew(notificationCalc);
    }

    @Override
    protected void doHandle(final ChainHandlingContext context) {
        final NotificationCalc notificationCalc = context.getNotificationCalc();
        final MessageEntity previous = context.getPrevious();
        final MessageEntity current = context.getCurrent();
        log.debug("save new notification: {}", notificationCalc.getNotificationSourceId());
        Not_Notification notification = notificationCalc.createNotification(previous, current);
        notification = super.notificationServiceManager.save(notification);
        if (notification.getStatus() == ACTIVE) {
            super.notificationHookManager.activateHook(notificationCalc, current);
        }
    }
}
