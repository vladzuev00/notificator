package com.aurorasoft.notificator.config;

import by.aurorasoft.replicator.annotation.EnableReplication;
import by.aurorasoft.replicator.model.setting.ReplicationConsumeSetting;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import com.aurorasoft.notificator.crud.repository.GeofenceRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableReplication
public class ReplicationConfig {

    @Bean
    public LongDeserializer longDeserializer() {
        return new LongDeserializer();
    }

    @Bean
    public ReplicationConsumeSetting<GeofenceEntity, Long> geofenceReplicationConsumeSetting(
            @Value("${replication.consume.topic.geofence}") String topic,
            GeofenceRepository repository
    ) {
        return new ReplicationConsumeSetting<>(
                topic,
                repository,
                longDeserializer(),
                new TypeReference<>() {
                }
        );
    }
}
