package by.aurorasoft.notificator.bymessage.vo;

import by.nhorushko.crudgeneric.domain.AbstractDto;
import com.locator.server.domain.entity.notification.NotificationType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Setter
@Getter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PROTECTED)
@Builder
public class NotificationSource implements AbstractDto {
    Long id;
    int pending;
    String name;
    NotificationType type;
    Map<String, Object> params;
    Not_NotificationSourceActionCommand actionCommand;
    Set<Not_Geofence> geofences = new HashSet<>();
    Set<Unit> units = new HashSet<>();
    Not_Schedule timeFilter;

    public NotificationSource(Long id, String name, NotificationType type, Map<String, Object> params,
                              Not_NotificationSourceActionCommand actionCommand, int pending, Not_Schedule timeFilter) {
        this.id = id;
        this.pending = pending;
        this.name = name;
        this.type = type;
        this.params = params;
        this.actionCommand = actionCommand;
        this.timeFilter = timeFilter;
    }

    public NotificationSource(Long id, final int pending, String name, NotificationType type, Map<String, Object> params,
                              Not_NotificationSourceActionCommand actionCommand, final Set<Not_Geofence> geofences,
                              final Set<Unit> units, Not_Schedule timeFilter) {
        this.id = id;
        this.pending = pending;
        this.name = name;
        this.type = type;
        this.params = params;
        this.actionCommand = actionCommand;
        this.geofences = geofences;
        this.units = units;
        this.timeFilter = timeFilter;
    }

    public void addGeofence(Not_Geofence g){
        geofences.add(g);
    }

    public void addGeofence(Collection<Not_Geofence> g) {
        geofences.addAll(g);
    }

    @Override
    public String toString() {
        return "NotificationSource{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", params=" + params +
                ", actionCommand=" + actionCommand +
                ", geofences[size]=" + geofences.size() +
                ", units[size]=" + units.size() +
                '}';
    }
}

