package by.aurorasoft.notificator.bymessage.handlingchain;

import org.springframework.stereotype.Component;

@Component
public final class StarterNotificationHandler extends NotificationHandler {

    public StarterNotificationHandler(final DisableNotificationHandler nextHandler) {
        super(nextHandler, null, null);
    }

    @Override
    protected boolean isHandleable(final ChainHandlingContext context) {
        return false;
    }

    @Override
    protected void doHandle(final ChainHandlingContext context) {
        throw new UnsupportedOperationException();
    }
}
