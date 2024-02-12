package by.aurorasoft.notificator.bymessage.handlingchain;

import com.locator.server.notification.common.service.Not_NotificationServiceManager;

public abstract class NotificationHandler {
    private final NotificationHandler nextHandler;
    protected final Not_NotificationServiceManager notificationServiceManager;
    protected final NotificationHookManager notificationHookManager;

    public NotificationHandler(final NotificationHandler nextHandler,
                               final Not_NotificationServiceManager notificationServiceManager,
                               final NotificationHookManager notificationHookManager) {
        this.nextHandler = nextHandler;
        this.notificationServiceManager = notificationServiceManager;
        this.notificationHookManager = notificationHookManager;
    }

    public void handle(final ChainHandlingContext context) {
        if (this.isHandleable(context)) {
            this.doHandle(context);
            return;
        }
        this.fireNextHandler(context);
    }

    protected abstract boolean isHandleable(final ChainHandlingContext context);

    protected abstract void doHandle(final ChainHandlingContext context);

    protected void fireNextHandler(final ChainHandlingContext context) {
        if (this.nextHandler != null) {
            this.nextHandler.handle(context);
        }
    }
}
