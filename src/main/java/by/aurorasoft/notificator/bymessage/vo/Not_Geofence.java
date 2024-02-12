package by.aurorasoft.notificator.bymessage.vo;

import by.nhorushko.crudgeneric.domain.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Not_Geofence implements AbstractDto {
    Long id;
    Geometry geometry;
}
