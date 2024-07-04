package com.aurorasoft.notificator.config;

import by.aurorasoft.replicator.model.pipeline.ReplicationConsumePipeline;
import com.aurorasoft.notificator.config.property.GeofenceReplicationConsumeProperty;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import com.aurorasoft.notificator.crud.repository.GeofenceRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.apache.kafka.common.serialization.Serdes.Long;

@Configuration
public class ReplicationConsumeConfig {

    @Bean
    public ReplicationConsumePipeline<GeofenceEntity, Long> geofencePipeline(GeofenceReplicationConsumeProperty property,
                                                                             GeofenceRepository repository) {
        return ReplicationConsumePipeline.<GeofenceEntity, Long>builder()
                .id(property.getPipelineId())
                .topic(property.getTopicName())
                .idSerde(Long())
                .replicationTypeReference(new TypeReference<>() {
                })
                .repository(repository)
                .build();
    }
}
