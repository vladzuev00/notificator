package by.aurorasoft.notificator.crud.entity;

import by.aurorasoft.notificator.model.UnitStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.SQLRestriction;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

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
public class UnitEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "status")
    private UnitStatus status;
}
