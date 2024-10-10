package com.aurorasoft.notificator.it.replication.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReplicationTopicConfig {
    private static final int TOPIC_PARTITIONS_COUNT = 1;
    private static final short TOPIC_REPLICATION_FACTOR = 1;

    @Bean
    public NewTopic geofenceReplicationTopic(@Value("${replication.consume.topic.geofence}") String name) {
        return createTopic(name);
    }

    @Bean
    public NewTopic unitReplicationTopic(@Value("${replication.consume.topic.unit}") String name) {
        return createTopic(name);
    }

    private NewTopic createTopic(String name) {
        return new NewTopic(name, TOPIC_PARTITIONS_COUNT, TOPIC_REPLICATION_FACTOR);
    }
}
