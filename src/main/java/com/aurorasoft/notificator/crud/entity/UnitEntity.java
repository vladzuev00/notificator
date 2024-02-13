package com.aurorasoft.notificator.crud.entity;

import com.aurorasoft.notificator.model.UnitStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLRestriction;

import static jakarta.persistence.EnumType.STRING;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;
import static org.hibernate.type.SqlTypes.NAMED_ENUM;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "unit")
@Cacheable
@Cache(usage = READ_WRITE)
@SQLRestriction("deleted = false")
public class UnitEntity extends AbstractEntity<Long> {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Enumerated(STRING)
    @Column(name = "status")
    @JdbcTypeCode(NAMED_ENUM)
    private UnitStatus status;
}
