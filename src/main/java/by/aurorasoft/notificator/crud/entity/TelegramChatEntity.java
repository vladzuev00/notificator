//package by.aurorasoft.notificator.crud.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//package com.locator.server.domain.entity;
//
//import by.nhorushko.crudgeneric.domain.AbstractEntity;
//import com.locator.server.domain.entity.notification.NotificationSourceEntity;
//import lombok.*;
//import lombok.experimental.FieldDefaults;
//import org.hibernate.Hibernate;
//
//import javax.persistence.*;
//import java.time.Instant;
//import java.util.List;
//import java.util.Objects;
//
//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
//@Entity
//@Table(name = "telegram")
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class TelegramChatEntity implements AbstractEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;
//
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    UserEntity user;
//
//    @Embedded
//    TelegramUserEntity telegramUser;
//
//    @Column(name = "is_activated")
//    boolean isActivated = false;
//
//    @ManyToMany(mappedBy = "telegramChats")
//    @ToString.Exclude
//    private List<NotificationSourceEntity> notificationSources;
//
//    @Column(name = "created")
//    Instant created = Instant.now();
//
//    @PreRemove
//    public void preRemove(){
//        notificationSources.forEach(s -> s.getTelegramChats().remove(this));
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        TelegramChatEntity that = (TelegramChatEntity) o;
//        return id != null && Objects.equals(id, that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
//}
//
