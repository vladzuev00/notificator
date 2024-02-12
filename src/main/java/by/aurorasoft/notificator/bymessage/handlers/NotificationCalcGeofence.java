package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.domain.entity.MessageEntity;
import com.locator.server.geofence.service.JtsGeometryFactory;
import com.locator.server.notification.bymessage.vo.Not_Geofence;
import lombok.Getter;
import org.locationtech.jts.geom.Geometry;

import java.util.Collection;

@Getter
public abstract class NotificationCalcGeofence extends NotificationCalc {

    public NotificationCalcGeofence(Meta meta, boolean isContinuable) {
        super(meta, isContinuable);
    }

    @Override
    public boolean isActivate(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        return isActive(prev, current, calculator);
    }

    @Override
    protected boolean isActive(MessageEntity prev, MessageEntity current, SensorBunchCalculator calculator) {
        if (!current.isValid()) {
            return false;
        }
        Geometry a = JtsGeometryFactory.point(prev);
        Geometry b = JtsGeometryFactory.point(current);
        if (a.equals(b)) {
            return false;
        }
        return handleIntersect(meta.getSource().getGeofences(), a, b);
    }

    protected abstract boolean handleIntersect(Collection<Not_Geofence> geofences, Geometry pointA, Geometry PointB);

    protected boolean intersectFull(Geometry polygon, Geometry pointA, Geometry pointB) {

        return !pointA.within(polygon) && !pointB.within(polygon)
                && JtsGeometryFactory.line(pointA, pointB).intersects(polygon);
    }
}
