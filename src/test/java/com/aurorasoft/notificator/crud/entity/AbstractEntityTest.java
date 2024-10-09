package com.aurorasoft.notificator.crud.entity;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public final class AbstractEntityTest extends AbstractSpringBootTest {

    @Test
    public void entitiesShouldBeEqual() {
        Long givenEntityId = 255L;
        AbstractEntity<Long> firstGivenEntity = createEntity(givenEntityId);
        AbstractEntity<Long> secondGivenEntity = createEntity(givenEntityId);

        boolean actual = firstGivenEntity.equals(secondGivenEntity);
        assertTrue(actual);
    }

    @Test
    public void sameEntitiesShouldBeEqual() {
        AbstractEntity<Long> givenEntity = createEntity(256L);

        @SuppressWarnings("EqualsWithItself") boolean actual = givenEntity.equals(givenEntity);
        assertTrue(actual);
    }

    @Test
    public void notProxyEntityShouldBeEqualProxyEntity() {
        Long givenEntityId = 255L;
        AbstractEntity<Long> firstGivenEntity = TimeZoneEntity.builder().id(givenEntityId).build();
        AbstractEntity<Long> secondGivenEntity = entityManager.find(TimeZoneEntity.class, givenEntityId);

        boolean actual = firstGivenEntity.equals(secondGivenEntity);
        assertTrue(actual);
    }

    @Test
    public void entitiesShouldNotBeEqualBecauseOfOtherEntityIsNull() {
        AbstractEntity<Long> firstGivenEntity = createEntity(255L);
        AbstractEntity<Long> secondGivenEntity = null;

        @SuppressWarnings("all") boolean actual = firstGivenEntity.equals(secondGivenEntity);
        assertFalse(actual);
    }

    @Test
    public void entitiesShouldNotBeEqualBecauseOfDifferentNotProxyTypes() {
        Long givenEntityId = 255L;
        AbstractEntity<Long> firstGivenEntity = createEntity(givenEntityId);
        AbstractEntity<Long> secondGivenEntity = entityManager.find(TimeZoneEntity.class, givenEntityId);

        boolean actual = firstGivenEntity.equals(secondGivenEntity);
        assertFalse(actual);
    }

    @Test
    public void entitiesShouldNotBeEqual() {
        AbstractEntity<Long> firstGivenEntity = createEntity(255L);
        AbstractEntity<Long> secondGivenEntity = createEntity(256L);

        boolean actual = firstGivenEntity.equals(secondGivenEntity);
        assertFalse(actual);
    }

    @Test
    public void hashCodeShouldBeFound() {
        AbstractEntity<Long> givenEntity = createEntity(255L);

        int actual = givenEntity.hashCode();
        int expected = 286;
        assertEquals(expected, actual);
    }

    private AbstractEntity<Long> createEntity(Long id) {
        AbstractEntity<Long> entity = new TestEntity();
        entity.setId(id);
        return entity;
    }

    @Setter
    @Getter
    private static final class TestEntity extends AbstractEntity<Long> {
        private Long id;
    }
}
