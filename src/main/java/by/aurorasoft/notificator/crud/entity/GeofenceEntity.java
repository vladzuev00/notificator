package by.aurorasoft.notificator.crud.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.locationtech.jts.geom.Geometry;

import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "geofence")
@Cacheable
@Cache(usage = READ_WRITE)
public class GeofenceEntity extends AbstractEntity<Long> {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "color")
    private String color;

    @Column(name = "max_speed")
    private int maxSpeed;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "boundaries")
    private Geometry boundaries;
}
