package com.aurorasoft.notificator.it.replication.base;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import com.aurorasoft.notificator.crud.entity.AbstractEntity;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@Transactional(propagation = NOT_SUPPORTED)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public abstract class ReplicationConsumeIT<E extends AbstractEntity<ID>, ID> extends AbstractSpringBootTest {
    private final KafkaTemplate<ID, String> kafkaTemplate;
    private final String topic;
    private final JpaRepository<E, ID> repository;
    private final ReplicationDeliveryBarrier replicationDeliveryBarrier;

    public ReplicationConsumeIT(String bootstrapAddress,
                                Serializer<ID> entityIdSerializer,
                                String topic,
                                JpaRepository<E, ID> repository,
                                ReplicationDeliveryBarrier replicationDeliveryBarrier) {
        kafkaTemplate = createKafkaTemplate(bootstrapAddress, entityIdSerializer);
        this.topic = topic;
        this.repository = repository;
        this.replicationDeliveryBarrier = replicationDeliveryBarrier;
    }

    @Test
    public final void testSave() {
        sendSaveAwaitingDelivery();
        Optional<E> optionalActual = findActualEntity();
        assertTrue(optionalActual.isPresent());
        E actual = optionalActual.get();
        E expected = getEntity();
        assertEquals(expected, actual);
    }

    @Test
    public final void testDelete() {
        repository.save(getEntity());
        sendDeleteAwaitingDelivery();
        Optional<E> optionalActual = findActualEntity();
        assertTrue(optionalActual.isEmpty());
    }

    protected abstract E getEntity();

    protected abstract String getSaveReplicationJson();

    protected abstract String getDeleteReplicationJson();

    protected abstract void assertEquals(E expected, E actual);

    private KafkaTemplate<ID, String> createKafkaTemplate(String bootstrapAddress, Serializer<ID> idSerializer) {
        Map<String, Object> configsByKeys = Map.of(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaTemplate<>(
                new DefaultKafkaProducerFactory<>(
                        configsByKeys,
                        idSerializer,
                        new StringSerializer()
                )
        );
    }

    private void sendSaveAwaitingDelivery() {
        sendReplicationAwaitingDelivery(getSaveReplicationJson());
    }

    private void sendDeleteAwaitingDelivery() {
        sendReplicationAwaitingDelivery(getDeleteReplicationJson());
    }

    private void sendReplicationAwaitingDelivery(String replicationJson) {
        replicationDeliveryBarrier.expect(1);
        kafkaTemplate.send(new ProducerRecord<>(topic, getEntityId(), replicationJson));
        replicationDeliveryBarrier.await();
    }

    private Optional<E> findActualEntity() {
        return repository.findById(getEntityId());
    }

    private ID getEntityId() {
        return getEntity().getId();
    }

    protected static abstract class ReplicationDeliveryBarrier {
        private static final long TIMEOUT_MS = 20000;

        private volatile CountDownLatch latch = new CountDownLatch(0);

        public final void expect(int replicationCount) {
            latch = new CountDownLatch(replicationCount);
        }

        public void onFinishRepositoryMethod() {
            latch.countDown();
        }

        public final void await() {
            try {
                boolean timeoutExceeded = !latch.await(TIMEOUT_MS, MILLISECONDS);
                if (timeoutExceeded) {
                    throw new IllegalStateException("Latch timeout was exceeded");
                }
            } catch (InterruptedException cause) {
                throw new IllegalStateException(cause);
            }
        }
    }
}
