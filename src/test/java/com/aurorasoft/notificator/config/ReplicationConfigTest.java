package com.aurorasoft.notificator.config;

import by.aurorasoft.replicator.model.setting.ReplicationConsumeSetting;
import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import com.aurorasoft.notificator.crud.repository.GeofenceRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public final class ReplicationConfigTest extends AbstractSpringBootTest {

    @Autowired
    private LongDeserializer longDeserializer;

    @Autowired
    private ReplicationConsumeSetting<GeofenceEntity, Long> geofenceSetting;

    @Value("${replication.consume.topic.geofence}")
    private String geofenceTopic;

    @Autowired
    private GeofenceRepository geofenceRepository;

    @Test
    public void longDeserializerShouldBeCreated() {
        assertNotNull(longDeserializer);
    }

    @Test
    public void geofenceConsumeSettingShouldBeCreated() {
        ReplicationConsumeSetting<GeofenceEntity, Long> expected = new ReplicationConsumeSetting<>(
                geofenceTopic,
                geofenceRepository,
                longDeserializer,
                new TypeReference<>() {
                }
        );
        assertEquals(geofenceSetting, expected);
    }

    private void assertEquals(ReplicationConsumeSetting<?, ?> expected, ReplicationConsumeSetting<?, ?> actual) {
        Assertions.assertEquals(expected.getTopic(), actual.getTopic());
        assertSame(expected.getRepository(), actual.getRepository());
        assertSame(expected.getIdDeserializer(), actual.getIdDeserializer());
        Assertions.assertEquals(getTypeName(expected), getTypeName(actual));
    }

    private String getTypeName(ReplicationConsumeSetting<?, ?> setting) {
        return setting.getReplicationTypeReference().getType().getTypeName();
    }
}
