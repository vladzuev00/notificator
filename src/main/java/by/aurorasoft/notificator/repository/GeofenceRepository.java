package by.aurorasoft.notificator.repository;

import by.aurorasoft.notificator.crud.entity.GeofenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeofenceRepository extends JpaRepository<GeofenceEntity, Long> {

}
