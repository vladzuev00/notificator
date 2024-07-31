package com.aurorasoft.notificator.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "telegram")
public class TelegramChatEntity extends AbstractEntity<Long> {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "activated")
    boolean activated;

    @Column(name = "created")
    Instant created;
}
