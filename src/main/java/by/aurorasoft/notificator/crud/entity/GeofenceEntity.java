package by.aurorasoft.notificator.crud.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.locationtech.jts.geom.Geometry;

import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@Setter
@Getter
@ToString
@Entity
@Table(name = "geofence")
@Cacheable
@Cache(usage = READ_WRITE)
public class GeofenceEntity extends AbstractEntity<Long> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "color")
    private String color = "#3ec78e";  //TODO: try remove

    @Column(name = "max_speed")
    private int maxSpeed = 0;          //TODO: try remove

    @Column(name = "user_id")
    private long userId;

    @Column(name = "boundaries")
    private Geometry boundaries;
}
