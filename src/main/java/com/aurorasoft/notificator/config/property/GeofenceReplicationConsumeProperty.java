package com.aurorasoft.notificator.config.property;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@ConfigurationProperties("replication.geofence")
public class GeofenceReplicationConsumeProperty {
    String topicName;
    String pipelineId;
}
