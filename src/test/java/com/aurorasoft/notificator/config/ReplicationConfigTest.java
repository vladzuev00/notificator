package com.aurorasoft.notificator.config;

import by.aurorasoft.replicator.model.pipeline.ReplicationConsumePipeline;
import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import com.aurorasoft.notificator.crud.entity.TimeZoneEntity;
import com.aurorasoft.notificator.crud.repository.GeofenceRepository;
import com.aurorasoft.notificator.crud.repository.TimeZoneRepository;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.junit.jupiter.api.Assertions.*;

public final class ReplicationConfigTest extends AbstractSpringBootTest {

    @Autowired
    private LongDeserializer longDeserializer;

    @Autowired
    @Qualifier("geofencePipeline")
    private ReplicationConsumePipeline<GeofenceEntity, Long> geofencePipeline;

    @Autowired
    @Qualifier("timeZonePipeline")
    private ReplicationConsumePipeline<TimeZoneEntity, Long> timeZonePipeline;

    @Value("${replication.consume.topic.geofence}")
    private String geofenceTopic;

    @Value("${replication.consume.topic.time-zone}")
    private String timeZoneTopic;

    @Autowired
    private GeofenceRepository geofenceRepository;

    @Autowired
    private TimeZoneRepository timeZoneRepository;

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
