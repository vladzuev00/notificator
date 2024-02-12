package by.aurorasoft.notificator.bymessage.service;

import by.aurorasoft.kafka.producer.KafkaProducerGeneric;
import by.aurorasoft.kafka.variables.KafkaVars;
import com.locator.server.notification.bymessage.vo.Not_Notification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerNot_NotificationBacklog extends KafkaProducerGeneric<Long, Long, Not_Notification> {

    public KafkaProducerNot_NotificationBacklog(KafkaTemplate<Long, Long> kafkaTemplate) {
        super(KafkaVars.NOTIFICATION_BACKLOG_TOPIC_NAME, kafkaTemplate);
    }

    @Override
    public void send(Not_Notification notificationDto) {
        sendModel(notificationDto.getUnit().getId(), notificationDto);
    }

    @Override
    protected Long convertTransportableToTopicValue(Not_Notification notificationDto) {
        return notificationDto.getId();
    }
}
