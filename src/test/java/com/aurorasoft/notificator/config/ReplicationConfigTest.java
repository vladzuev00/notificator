package com.aurorasoft.notificator.config;

import by.aurorasoft.replicator.model.pipeline.ReplicationConsumePipeline;
import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import com.aurorasoft.notificator.crud.entity.TelegramChatEntity;
import com.aurorasoft.notificator.crud.entity.TimeZoneEntity;
import com.aurorasoft.notificator.crud.repository.GeofenceRepository;
import com.aurorasoft.notificator.crud.repository.TelegramChatRepository;
import com.aurorasoft.notificator.crud.repository.TimeZoneRepository;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.junit.jupiter.api.Assertions.*;

public final class ReplicationConfigTest extends AbstractSpringBootTest {

    @Autowired
    private LongDeserializer longDeserializer;

    @Autowired
    private ReplicationConsumePipeline<GeofenceEntity, Long> geofencePipeline;

    @Autowired
    private ReplicationConsumePipeline<TimeZoneEntity, Long> timeZonePipeline;

    @Autowired
    private ReplicationConsumePipeline<TelegramChatEntity, Long> telegramChatPipeline;

    @Value("${replication.consume.topic.geofence}")
    private String geofenceTopic;

    @Value("${replication.consume.topic.time-zone}")
    private String timeZoneTopic;

    @Value("${replication.consume.topic.telegram-chat}")
    private String telegramChatTopic;

    @Autowired
    private GeofenceRepository geofenceRepository;

    @Autowired
    private TimeZoneRepository timeZoneRepository;

    @Autowired
    private TelegramChatRepository telegramChatRepository;

    @Test
    public void longDeserializerShouldBeCreated() {
        assertNotNull(longDeserializer);
    }

    @Test
    public void geofencePipelineShouldBeCreated() {
        verifyPipeline(
                geofencePipeline,
                geofenceTopic,
                LongDeserializer.class,
                "by.aurorasoft.replicator.model.replication.consumed.ConsumedReplication<com.aurorasoft.notificator.crud.entity.GeofenceEntity, java.lang.Long>",
                geofenceRepository
        );
    }

    @Test
    public void timeZonePipelineShouldBeCreated() {
        verifyPipeline(
                timeZonePipeline,
                timeZoneTopic,
                LongDeserializer.class,
                "by.aurorasoft.replicator.model.replication.consumed.ConsumedReplication<com.aurorasoft.notificator.crud.entity.TimeZoneEntity, java.lang.Long>",
                timeZoneRepository
        );
    }

    @Test
    public void telegramChatPipelineShouldBeCreated() {
        verifyPipeline(
                telegramChatPipeline,
                telegramChatTopic,
                LongDeserializer.class,
                "by.aurorasoft.replicator.model.replication.consumed.ConsumedReplication<com.aurorasoft.notificator.crud.entity.TelegramChatEntity, java.lang.Long>",
                telegramChatRepository
        );
    }

    private void verifyPipeline(ReplicationConsumePipeline<?, ?> actual,
                                String expectedTopic,
                                @SuppressWarnings("SameParameterValue") Class<? extends Deserializer<?>> expectedIdDeserializerType,
                                String expectedTypeName,
                                JpaRepository<?, ?> expectedRepository) {
        assertNotNull(actual);

        String actualTopic = actual.getTopic();
        assertSame(expectedTopic, actualTopic);

        Class<?> actualIdDeserializerType = actual.getIdDeserializer().getClass();
        assertSame(expectedIdDeserializerType, actualIdDeserializerType);

        String actualTypeName = actual.getReplicationTypeReference()
                .getType()
                .getTypeName();
        assertEquals(expectedTypeName, actualTypeName);

        JpaRepository<?, ?> actualRepository = actual.getRepository();
        assertSame(expectedRepository, actualRepository);
    }
}
