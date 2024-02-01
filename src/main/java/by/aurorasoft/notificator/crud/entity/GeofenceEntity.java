package by.aurorasoft.notificator.crud.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE;

@Getter
@Setter
@ToString
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
    private Long userId;

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
