package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.geofence.service.JtsGeometryFactory;
import com.locator.server.notification.bymessage.vo.Not_Geofence;
import org.locationtech.jts.geom.Geometry;

import java.util.Collection;

public class NotificationCalcGeofenceWithin extends NotificationCalcGeofence {

    public NotificationCalcGeofenceWithin(Meta meta, boolean isContinuable) {
        super(meta, isContinuable);
    }

    @Override
    protected boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        if (!current.isValid()) {
            return false;
        }
        Geometry a = JtsGeometryFactory.point(prev);
        Geometry b = JtsGeometryFactory.point(current);
        return handleIntersect(meta.getSource().getGeofences(), a, b);
    }

    @Override
    protected boolean handleIntersect(Collection<Not_Geofence> geofences, Geometry pointA, Geometry pointB) {
        for (Not_Geofence g : geofences) {
            if (pointA.within(g.getGeometry()) && pointB.within(g.getGeometry())) {
                return true;
            }
        }
        return false;
    }
}
