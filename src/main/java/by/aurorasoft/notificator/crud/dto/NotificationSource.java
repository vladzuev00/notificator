package by.aurorasoft.notificator.crud.dto;

import by.aurorasoft.notificator.model.NotificationSourceActionCommand;
import by.aurorasoft.notificator.model.NotificationType;
import by.aurorasoft.notificator.model.Schedule;
import by.nhorushko.crudgeneric.v2.domain.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.Map;
import java.util.Set;

@Value
@AllArgsConstructor
@Builder
public class NotificationSource implements AbstractDto<Long> {
    Long id;
    int pendingSeconds;
    String name;
    NotificationType type;
    Map<String, Object> params;
    NotificationSourceActionCommand actionCommand;
    Set<Geofence> geofences;
    Set<Unit> units;
    Schedule timeFilter;
}
