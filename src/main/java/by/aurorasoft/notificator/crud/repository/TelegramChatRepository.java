package by.aurorasoft.notificator.crud.repository;

import by.aurorasoft.notificator.crud.entity.TelegramChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramChatRepository extends JpaRepository<TelegramChatEntity, Long> {

}
