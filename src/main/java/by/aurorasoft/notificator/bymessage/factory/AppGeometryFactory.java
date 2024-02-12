package by.aurorasoft.notificator.bymessage.factory;

import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.notification.bymessage.vo.Not_Geofence;
import lombok.SneakyThrows;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.WKTReader;

public class AppGeometryFactory {
    private static final int DEFAULT_SRID = 4326;
    private static final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), DEFAULT_SRID);
    private static final WKTReader wktReader = new WKTReader();

    @SneakyThrows
    public static Not_Geofence geofence(Long id, String wkt) {
        Geometry geometry = wktReader.read(wkt);
        geometry.setSRID(DEFAULT_SRID);
        return new Not_Geofence(id, geometry);
    }

    public static Geometry point(float longitude, float latitude) {
        return geometryFactory.createPoint(coordinate(longitude, latitude));
    }

    public static Geometry line(Geometry a, Geometry b) {
        return geometryFactory.createLineString(new Coordinate[]{a.getCoordinate(), b.getCoordinate()});
    }

    public static Geometry point(MessageEntity m) {
        return geometryFactory.createPoint(coordinate(m.getLongitude(), m.getLatitude()));
    }

    private static Coordinate coordinate(float longitude, float latitude) {
        return new Coordinate(longitude, latitude);
    }
}
