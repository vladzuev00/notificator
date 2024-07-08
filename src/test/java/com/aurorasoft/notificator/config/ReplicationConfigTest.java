package com.aurorasoft.notificator.config;

import by.aurorasoft.replicator.model.pipeline.ReplicationConsumePipeline;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import com.aurorasoft.notificator.crud.repository.GeofenceRepository;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.junit.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public final class ReplicationConfigTest {
    private final ReplicationConfig config = new ReplicationConfig();

    @Test
    public void longDeserializerShouldBeCreated() {
        LongDeserializer actual = config.longDeserializer();
        assertNotNull(actual);
    }

    @Test
    public void geofencePipelineShouldBeCreated() {
        String givenTopic = "geofence-sync";
        GeofenceRepository givenRepository = mock(GeofenceRepository.class);

        ReplicationConsumePipeline<GeofenceEntity, Long> actual = config.geofencePipeline(givenTopic, givenRepository);
        assertNotNull(actual);

        String actualTopic = actual.getTopic();
        assertSame(givenTopic, actualTopic);

        Class<?> actualDeserializerType = actual.getIdDeserializer().getClass();
        Class<?> expectedDeserializerType = LongDeserializer.class;
        assertSame(expectedDeserializerType, actualDeserializerType);

        String actualTypeName = actual.getReplicationTypeReference().getType().getTypeName();
        String expectedTypeName = "by.aurorasoft.replicator.model.replication.consumed.ConsumedReplication<"
                + "com.aurorasoft.notificator.crud.entity.GeofenceEntity, "
                + "java.lang.Long"
                + ">";
        assertEquals(expectedTypeName, actualTypeName);

        JpaRepository<GeofenceEntity, Long> actualRepository = actual.getRepository();
        assertSame(givenRepository, actualRepository);
    }
}
