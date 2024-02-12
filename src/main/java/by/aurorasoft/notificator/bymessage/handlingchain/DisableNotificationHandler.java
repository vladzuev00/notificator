package by.aurorasoft.notificator.bymessage.handlingchain;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.common.service.Not_NotificationServiceManager;
import org.springframework.stereotype.Component;

@Component
public final class DisableNotificationHandler extends NotificationHandler {

    public DisableNotificationHandler(final ActivePendingNotificationHandler nextHandler,
                                      final Not_NotificationServiceManager manager,
                                      final NotificationHookManager notificationHookManager) {
        super(nextHandler, manager, notificationHookManager);
    }

    @Override
    protected boolean isHandleable(final ChainHandlingContext context) {
        final NotificationCalc notificationCalc = context.getNotificationCalc();
        final MessageEntity previous = context.getPrevious();
        final MessageEntity current = context.getCurrent();
        final SensorBunchCalculator calculator = context.getCalculator();
        return !notificationCalc.isActivate(previous, current, calculator);
    }

    @Override
    protected void doHandle(final ChainHandlingContext context) {
        final NotificationCalc notificationCalc = context.getNotificationCalc();
        final MessageEntity current = context.getCurrent();
        super.notificationServiceManager.getActive(notificationCalc).ifPresent(notification -> {
            super.notificationServiceManager.updateToCompleted(notification, current);
            super.notificationHookManager.deactivateHook(notificationCalc, current);
        });
        super.notificationServiceManager.getPending(notificationCalc)
                .ifPresent(notification -> super.notificationServiceManager.updateToCancelled(notification, current));
    }
}
