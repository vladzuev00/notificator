package by.aurorasoft.notificator.crud.repository;

import by.aurorasoft.notificator.crud.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

}
