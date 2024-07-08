package com.aurorasoft.notificator.crud.entity;

import com.aurorasoft.notificator.base.AbstractSpringBootTest;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import static org.junit.Assert.*;

//TODO: do commented tests with UnitEntity
public final class AbstractEntityTest extends AbstractSpringBootTest {

    @Test
    public void entitiesShouldBeEqual() {
        AbstractEntity<Long> firstGivenEntity = createEntity(255L);
        AbstractEntity<Long> secondGivenEntity = createEntity(255L);

        boolean actual = firstGivenEntity.equals(secondGivenEntity);
        assertTrue(actual);
    }

    @Test
    @SuppressWarnings("EqualsWithItself")
    public void sameEntitiesShouldBeEqual() {
        AbstractEntity<Long> givenEntity = createEntity(256L);

        boolean actual = givenEntity.equals(givenEntity);
        assertTrue(actual);
    }

    //    @Test
//    public void notProxyEntityShouldBeEqualProxyEntity() {
//        final Entity<Long> firstGivenEntity = createTracker(255L);
//        final Entity<Long> secondGivenEntity = entityManager.find(TrackerEntity.class, 255L);
//
//        final boolean actual = firstGivenEntity.equals(secondGivenEntity);
//        assertTrue(actual);
//    }

    @Test
    public void entitiesShouldNotBeEqualBecauseOfOtherEntityIsNull() {
        AbstractEntity<Long> firstGivenEntity = createEntity(255L);
        AbstractEntity<Long> secondGivenEntity = null;

        @SuppressWarnings("all") boolean actual = firstGivenEntity.equals(secondGivenEntity);
        assertFalse(actual);
    }

    //    @Test
//    public void entitiesShouldNotBeEqualBecauseOfDifferentNotProxyTypes() {
//        final Entity<Long> firstGivenEntity = createEntity(255L);
//        final Entity<Long> secondGivenEntity = entityManager.find(TrackerEntity.class, 255L);
//
//        final boolean actual = firstGivenEntity.equals(secondGivenEntity);
//        assertFalse(actual);
//    }

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

    private static AbstractEntity<Long> createEntity(Long id) {
        AbstractEntity<Long> entity = new TestEntity();
        entity.setId(id);
        return entity;
    }

//    @SuppressWarnings("SameParameterValue")
//    private static TrackerEntity createTracker(final Long id) {
//        return TrackerEntity.builder()
//                .id(id)
//                .build();
//    }

    @Setter
    @Getter
    private static final class TestEntity extends AbstractEntity<Long> {
        private Long id;
    }
}
