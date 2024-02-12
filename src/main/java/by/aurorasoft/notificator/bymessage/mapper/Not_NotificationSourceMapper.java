package by.aurorasoft.notificator.bymessage.mapper;

import by.nhorushko.crudgeneric.mapper.AbstractMapper;
import by.nhorushko.crudgeneric.mapper.ImmutableDtoAbstractMapper;
import com.locator.server.domain.entity.GeofenceEntity;
import com.locator.server.domain.entity.notification.NotificationSourceEntity;
import com.locator.server.domain.entity.notification.NotificationType;
import com.locator.server.domain.entity.notification.ranges.Range;
import com.locator.server.domain.entity.notification.ranges.Schedule;
import com.locator.server.domain.entity.unit.UnitEntity;
import com.locator.server.notification.bymessage.vo.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.locator.server.domain.dto.notification.NotificationSource.ActionCommand;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Component
public final class Not_NotificationSourceMapper
        extends ImmutableDtoAbstractMapper<NotificationSourceEntity, Not_NotificationSource> {

    private final AbstractMapper<GeofenceEntity, Not_Geofence> geofenceMapper;
    private final AbstractMapper<UnitEntity, Not_Unit> unitMapper;

    public Not_NotificationSourceMapper(final ModelMapper modelMapper,
                                        final AbstractMapper<GeofenceEntity, Not_Geofence> geofenceMapper,
                                        final AbstractMapper<UnitEntity, Not_Unit> unitMapper) {
        super(NotificationSourceEntity.class, Not_NotificationSource.class, modelMapper);
        this.geofenceMapper = geofenceMapper;
        this.unitMapper = unitMapper;
    }

    @Override
    protected Not_NotificationSource mapDto(final NotificationSourceEntity entity) {
        final Long id = entity.getId();
        final int pending = entity.getPendingSeconds();
        final String name = entity.getName();
        final NotificationType type = entity.getType();
        final Map<String, Object> params = entity.getParams();

        final ActionCommand entityActionCommand = entity.getActionCommand();
        final Not_NotificationSourceActionCommand actionCommand = this.mapActionCommand(entityActionCommand);

        final List<GeofenceEntity> entityGeofences = entity.getGeofences();
        final Set<Not_Geofence> geofences = entityGeofences.stream()
                .map(this.geofenceMapper::toDto)
                .collect(toSet());

        final List<UnitEntity> entityUnits = entity.getUnits();
        final Set<Not_Unit> units = entityUnits.stream()
                .map(this.unitMapper::toDto)
                .collect(toSet());

        final Schedule<Range<Integer>> entityTimeFilter = entity.getTimeFilter();
        final Not_Schedule timeFilter = this.mapTimeFilter(entityTimeFilter);

        return Not_NotificationSource.builder()
                .id(id)
                .pending(pending)
                .name(name)
                .type(type)
                .params(params)
                .actionCommand(actionCommand)
                .geofences(geofences)
                .units(units)
                .timeFilter(timeFilter)
                .build();
    }

    private Not_NotificationSourceActionCommand mapActionCommand(final ActionCommand source) {
        if (source == null) {
            return null;
        }
        final ActionCommand.Action sourceActivate = source.getActivate();
        final Not_NotificationSourceActionCommand.Action activate = this.mapAction(sourceActivate);

        final ActionCommand.Action sourceDeactivate = source.getDeactivate();
        final Not_NotificationSourceActionCommand.Action deactivate = this.mapAction(sourceDeactivate);

        return new Not_NotificationSourceActionCommand(activate, deactivate);
    }

    private Not_NotificationSourceActionCommand.Action mapAction(final ActionCommand.Action source) {
        final String text = source.getText();
        final int timeout = source.getTimeout();
        return new Not_NotificationSourceActionCommand.Action(text, timeout);
    }

    private Not_Schedule mapTimeFilter(final Schedule<Range<Integer>> source) {
        final List<Range<Integer>> sourceRanges = source.getRanges();
        final List<Not_Schedule.Range> ranges = sourceRanges.stream()
                .map(this::mapRange)
                .collect(toList());
        return new Not_Schedule(ranges);
    }

    private Not_Schedule.Range mapRange(final Range<Integer> source) {
        final int from = source.getFrom();
        final int to = source.getTo();
        return new Not_Schedule.Range(from, to);
    }
}
