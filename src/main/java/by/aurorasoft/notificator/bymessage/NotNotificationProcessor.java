package by.aurorasoft.notificator.bymessage;

import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.notification.bymessage.handlingchain.ChainHandlingContext;
import com.locator.server.notification.bymessage.handlingchain.StarterNotificationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotNotificationProcessor {
    private final StarterNotificationHandler starterNotificationHandler;
    private final ChainHandlingContextsFactory chainHandlingContextFactory;

    @Transactional
    public void handle(final MessageEntity previous, final MessageEntity current) {
        final List<ChainHandlingContext> chainHandlingContexts = this.chainHandlingContextFactory
                .create(previous, current);
         chainHandlingContexts.forEach(this.starterNotificationHandler::handle);
    }
}

