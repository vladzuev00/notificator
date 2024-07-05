package com.aurorasoft.notificator.config;

import by.aurorasoft.replicator.model.pipeline.ReplicationConsumePipeline;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import com.aurorasoft.notificator.crud.repository.GeofenceRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.apache.kafka.common.serialization.Serdes.Long;

@Configuration
public class ReplicationConsumeConfig {

    @Bean
    public ReplicationConsumePipeline<GeofenceEntity, Long> geofencePipeline(@Value("${replication.consume.topic.geofence}") String topic,
                                                                             GeofenceRepository repository) {
        return ReplicationConsumePipeline.<GeofenceEntity, Long>builder()
                .topic(topic)
                .idSerde(Long())
                .replicationTypeReference(new TypeReference<>() {
                })
                .repository(repository)
                .build();
    }
}
