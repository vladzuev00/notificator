package com.aurorasoft.notificator.crud.repository;

import com.aurorasoft.notificator.crud.entity.TelegramChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramChatRepository extends JpaRepository<TelegramChatEntity, Long> {

}
