package com.aurorasoft.notificator.it.replication;

import com.aurorasoft.notificator.crud.entity.UnitEntity;
import com.aurorasoft.notificator.crud.repository.UnitRepository;
import com.aurorasoft.notificator.testutil.UnitEntityUtil;
import org.apache.kafka.common.serialization.LongSerializer;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.aurorasoft.notificator.model.UnitStatus.ACTIVE;

public final class UnitReplicationConsumeIT extends ReplicationConsumeIT<UnitEntity, Long> {

    @Autowired
    public UnitReplicationConsumeIT(@Value("${spring.kafka.bootstrap-servers}") String bootstrapAddress,
                                    @Value("${replication.consume.topic.unit}") String topic,
                                    UnitRepository repository,
                                    UnitReplicationDeliveryBarrier replicationDeliveryBarrier) {
        super(bootstrapAddress, new LongSerializer(), topic, repository, replicationDeliveryBarrier);
    }

    @Override
    protected UnitEntity getEntity() {
        return new UnitEntity(255L, "test-unit", "#111111", ACTIVE);
    }

    @Override
    protected String getSaveReplicationJson() {
        return """
                {
                   "type": "save",
                   "body": {
                     "id": 255,
                     "name": "test-unit",
                     "color": "#111111",
                     "status": "ACTIVE"
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
    protected void assertEquals(UnitEntity expected, UnitEntity actual) {
        UnitEntityUtil.assertEquals(expected, actual);
    }

    @Aspect
    @Component
    public static class UnitReplicationDeliveryBarrier extends ReplicationDeliveryBarrier {

        @After("unitRepository()")
        public void onAfterRepositoryMethod() {
            super.onAfterRepositoryMethod();
        }

        @Pointcut("target(com.aurorasoft.notificator.crud.repository.UnitRepository)")
        private void unitRepository() {

        }
    }
}
