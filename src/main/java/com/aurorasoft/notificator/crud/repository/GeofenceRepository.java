package com.aurorasoft.notificator.crud.repository;

import com.aurorasoft.notificator.crud.entity.GeofenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeofenceRepository extends JpaRepository<GeofenceEntity, Long> {

}
