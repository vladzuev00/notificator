package com.aurorasoft.notificator.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Immutable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Immutable
@Entity
@Table(name = "time_zone")
public class TimeZoneEntity extends AbstractEntity<Long> {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
