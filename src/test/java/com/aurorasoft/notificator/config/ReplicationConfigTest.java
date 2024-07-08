package com.aurorasoft.notificator.config;

import by.aurorasoft.replicator.model.pipeline.ReplicationConsumePipeline;
import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import com.aurorasoft.notificator.crud.repository.GeofenceRepository;
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

    @Value("${replication.consume.topic.geofence}")
    private String geofenceTopic;

    @Autowired
    private GeofenceRepository geofenceRepository;

    @Test
    public void longDeserializerShouldBeCreated() {
        assertNotNull(longDeserializer);
    }

    @Test
    public void geofencePipelineShouldBeCreated() {
        assertNotNull(geofencePipeline);

        String actualTopic = geofencePipeline.getTopic();
        assertSame(geofenceTopic, actualTopic);

        Class<?> actualDeserializerType = geofencePipeline.getIdDeserializer().getClass();
        Class<?> expectedDeserializerType = LongDeserializer.class;
        assertSame(expectedDeserializerType, actualDeserializerType);

        String actualTypeName = geofencePipeline.getReplicationTypeReference().getType().getTypeName();
        String expectedTypeName = "by.aurorasoft.replicator.model.replication.consumed.ConsumedReplication<"
                + "com.aurorasoft.notificator.crud.entity.GeofenceEntity, "
                + "java.lang.Long"
                + ">";
        assertEquals(expectedTypeName, actualTypeName);

        JpaRepository<GeofenceEntity, Long> actualRepository = geofencePipeline.getRepository();
        assertSame(geofenceRepository, actualRepository);
    }
}
