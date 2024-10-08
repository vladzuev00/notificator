package com.aurorasoft.notificator.crud.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
@Table(name = "geofence")
public class GeofenceEntity extends AbstractEntity<Long> {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "geometry")
    @JsonDeserialize(using = GeometryDeserializer.class)
    private Geometry geometry;
}
