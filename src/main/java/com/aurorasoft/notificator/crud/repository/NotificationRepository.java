package com.aurorasoft.notificator.crud.repository;

import com.aurorasoft.notificator.crud.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    @Override
    @Modifying
    @SuppressWarnings("NullableProblems")
    @Query("Update NotificationEntity e SET e.status = 'DELETED' WHERE e.id = :id")
    void deleteById(final Long id);

    @Query("SELECT DISTINCT e.unit.id FROM NotificationEntity e WHERE e.id IN (:ids)")
    List<Long> findUnitIds(final Collection<Long> ids);

    @Query("SELECT e FROM NotificationEntity e WHERE e.unit.id = :unitId AND e.status "
            + "IN (com.aurorasoft.notificator.model.NotificationStatus.ACTIVE, "
            + "com.aurorasoft.notificator.model.NotificationStatus.PENDING)")
    List<NotificationEntity> findAllActiveAndPendingByUnitId(final Long unitId);

    @Modifying
    @Query("UPDATE NotificationEntity e SET "
            + "e.status = com.aurorasoft.notificator.model.NotificationStatus.CANCELLED, "
            + "e.finishTime = :finishTime "
            + "WHERE e.source.id = :sourceId "
            + "AND e.status = com.aurorasoft.notificator.model.NotificationStatus.ACTIVE")
    void updateAllActiveOfSourceToCancelled(final Long sourceId, final Instant finishTime);

    @Modifying
    @Query("UPDATE NotificationEntity e SET e.read = :read WHERE e.id IN(:ids)")
    void updateRead(final Collection<Long> ids, final boolean read);

    @Modifying
    @Query(value = "UPDATE notification SET is_read = :read "
            + "WHERE source_id IN (SELECT id FROM notification_source WHERE user_id IN (SELECT id FROM aur_findalluserchilds(:userId)) AND deleted = FALSE) "
            + "AND unit_id = :unitId", nativeQuery = true)
    void updateReadOfUserAndUnit(final Long userId, final Long unitId, final boolean read);

    @Modifying
    @Query("UPDATE NotificationEntity e SET e.status = com.aurorasoft.notificator.model.NotificationStatus.DELETED "
            + "WHERE e.unit.id = :unitId AND e.startTime < :bound")
    void deleteByUnitIdAndStartTimeUpperBound(final Long unitId, final Instant bound);
}
