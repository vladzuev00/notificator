package com.aurorasoft.notificator.it.replication.geofence;

import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import com.aurorasoft.notificator.crud.repository.GeofenceRepository;
import com.aurorasoft.notificator.it.replication.base.ReplicationConsumeIT;
import com.aurorasoft.notificator.testutil.GeofenceEntityUtil;
import org.apache.kafka.common.serialization.LongSerializer;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public final class GeofenceReplicationConsumeIT extends ReplicationConsumeIT<GeofenceEntity, Long> {
    private final GeometryFactory geometryFactory;

    @Autowired
    public GeofenceReplicationConsumeIT(@Value("${spring.kafka.bootstrap-servers}") String bootstrapAddress,
                                        @Value("${replication.consume.topic.geofence}") String topic,
                                        GeofenceRepository repository,
                                        GeofenceReplicationDeliveryBarrier replicationDeliveryBarrier,
                                        GeometryFactory geometryFactory) {
        super(bootstrapAddress, new LongSerializer(), topic, repository, replicationDeliveryBarrier);
        this.geometryFactory = geometryFactory;
    }

    @Override
    protected GeofenceEntity getEntity() {
        return new GeofenceEntity(
                255L,
                geometryFactory.createPolygon(
                        new Coordinate[]{
                                new Coordinate(1, 2),
                                new Coordinate(1, 4),
                                new Coordinate(3, 4),
                                new Coordinate(3, 2),
                                new Coordinate(1, 2)
                        }
                )
        );
    }

    @Override
    protected String getSaveReplicationJson() {
        return """
                {
                  "type": "save",
                  "body": {
                    "geometry": {
                      "type": "Polygon",
                      "coordinates": [
                        [
                          [
                            1,
                            2
                          ],
                          [
                            1,
                            4
                          ],
                          [
                            3,
                            4
                          ],
                          [
                            3,
                            2
                          ],
                          [
                            1,
                            2
                          ]
                        ]
                      ]
                    },
                    "id": 255
                  }
                }""";
    }

    @Override
    protected String getDeleteReplicationJson() {
        return """
                {
                  "type": "delete",
                  "body": 255
                }""";
    }

    @Override
    protected void assertEquals(GeofenceEntity expected, GeofenceEntity actual) {
        GeofenceEntityUtil.assertEquals(expected, actual);
    }

    @Aspect
    @Component
    public static class GeofenceReplicationDeliveryBarrier extends ReplicationDeliveryBarrier {

        @After("geofenceRepository()")
        public void onFinishRepositoryMethod() {
            super.onFinishRepositoryMethod();
        }

        @Pointcut("target(com.aurorasoft.notificator.crud.repository.GeofenceRepository)")
        private void geofenceRepository() {

        }
    }
}
