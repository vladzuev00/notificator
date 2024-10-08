package com.aurorasoft.notificator.it.replication.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReplicationTopicConfig {

    @Bean
    public NewTopic geofenceReplicationTopic(@Value("${replication.consume.topic.geofence}") String name) {
        return new NewTopic(name, 1, (short) 1);
    }
}
