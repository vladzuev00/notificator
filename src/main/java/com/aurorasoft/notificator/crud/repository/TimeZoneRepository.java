package com.aurorasoft.notificator.crud.repository;

import com.aurorasoft.notificator.crud.entity.TimeZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeZoneRepository extends JpaRepository<TimeZoneEntity, Long> {

}
