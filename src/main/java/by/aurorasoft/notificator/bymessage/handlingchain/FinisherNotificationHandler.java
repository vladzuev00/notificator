package by.aurorasoft.notificator.bymessage.handlingchain;

import org.springframework.stereotype.Component;

@Component
public final class FinisherNotificationHandler extends NotificationHandler {

    public FinisherNotificationHandler() {
        super(null, null, null);
    }

    @Override
    protected boolean isHandleable(final ChainHandlingContext context) {
        return true;
    }

    @Override
    protected void doHandle(final ChainHandlingContext context) {
        throw new NoSuitableNotificationHandlerException();
    }

    public static final class NoSuitableNotificationHandlerException extends RuntimeException {
        public NoSuitableNotificationHandlerException() {
            super();
        }

        public NoSuitableNotificationHandlerException(final String description) {
            super(description);
        }

        public NoSuitableNotificationHandlerException(final Exception cause) {
            super(cause);
        }

        public NoSuitableNotificationHandlerException(final String description, final Exception cause) {
            super(description, cause);
        }
    }
}
