package com.aurorasoft.notificator.config;

import by.aurorasoft.replicator.annotation.EnableReplication;
import by.aurorasoft.replicator.model.setting.ReplicationConsumeSetting;
import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import com.aurorasoft.notificator.crud.entity.TelegramChatEntity;
import com.aurorasoft.notificator.crud.entity.TimeZoneEntity;
import com.aurorasoft.notificator.crud.repository.GeofenceRepository;
import com.aurorasoft.notificator.crud.repository.TelegramChatRepository;
import com.aurorasoft.notificator.crud.repository.TimeZoneRepository;
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

//    @Bean
//    public ReplicationConsumeSetting<TimeZoneEntity, Long> timeZoneReplicationConsumeSetting(
//            @Value("${replication.consume.topic.time-zone}") String topic,
//            TimeZoneRepository repository
//    ) {
//        return new ReplicationConsumeSetting<>(
//                topic,
//                repository,
//                longDeserializer(),
//                new TypeReference<>() {
//                }
//        );
//    }
//
//    @Bean
//    public ReplicationConsumePipeline<TelegramChatEntity, Long> telegramChatPipeline(@Value("${replication.consume.topic.telegram-chat}") String topic,
//                                                                                     TelegramChatRepository repository) {
//        return new ReplicationConsumePipeline<>(
//                topic,
//                longDeserializer(),
//                new TypeReference<>() {
//                },
//                repository
//        );
//    }
}
