package by.aurorasoft.notificator.bymessage.handlingchain;

import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.vo.Not_Notification;
import com.locator.server.notification.common.service.Not_NotificationServiceManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public final class ActivePendingNotificationHandler extends NotificationHandler {

    public ActivePendingNotificationHandler(final NewActiveNotificationHandler nextHandler,
                                            final Not_NotificationServiceManager notificationServiceManager,
                                            final NotificationHookManager notificationHookManager) {
        super(nextHandler, notificationServiceManager, notificationHookManager);
    }

    @Override
    protected boolean isHandleable(final ChainHandlingContext context) {
        final NotificationCalc notificationCalc = context.getNotificationCalc();
        return !super.notificationServiceManager.isNew(notificationCalc);
    }

    @Override
    protected void doHandle(final ChainHandlingContext context) {
        final NotificationCalc notificationCalc = context.getNotificationCalc();
        final MessageEntity current = context.getCurrent();
        final Optional<Not_Notification> optionalNotification = super.notificationServiceManager
                .getPending(notificationCalc);
        optionalNotification.ifPresent(notification -> {
            if (notificationCalc.isPendingEnd(notification, current)) {
                super.notificationServiceManager.updateToActive(notification);
                super.notificationHookManager.activateHook(notificationCalc, current);
            }
        });
    }
}
