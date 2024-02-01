package by.aurorasoft.notificator.crud.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cache;
import org.locationtech.jts.geom.Geometry;

import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Cacheable
@Table(name = "geofence")
@Cache(usage = READ_WRITE)
public class GeofenceEntity extends AbstractEntity<Long> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
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

    //TODO:
//    public void setBoundaries(Geometry g) {
//        if (g == null || g.getCoordinates().length == 0) {
//            throw new IllegalArgumentException(String.format("Geometry coordinates length should be more than 0. actual: %s", g));
//        }
//        this.boundaries = g;
//    }
}
