package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.notification.bymessage.vo.Not_Geofence;
import org.locationtech.jts.geom.Geometry;

import java.util.Collection;

public class NotificationCalcGeofenceExit extends NotificationCalcGeofence {

    public NotificationCalcGeofenceExit(Meta meta, boolean isContinuable) {
        super(meta, isContinuable);
    }

    @Override
    protected boolean handleIntersect(Collection<Not_Geofence> geofences, Geometry pointA, Geometry pointB) {
        for (Not_Geofence geofence : geofences) {
            Geometry g = geofence.getGeometry();
            if ((pointA.within(g) && !pointB.within(g)) || intersectFull(g, pointA, pointB)) {
                return true;
            }
        }
        return false;
    }
}
