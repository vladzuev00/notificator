package by.aurorasoft.notificator.bymessage.vo;

import by.aurorasoft.notificator.model.NotificationStatus;
import by.nhorushko.crudgeneric.domain.AbstractDto;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

import java.time.Instant;

@NonFinal
@Value
@AllArgsConstructor
public class Notification implements AbstractDto {
    Long id;
    Unit unit;
    NotificationSource source;
    Instant startTime;
    Instant finishTime;
    NotificationStatus status;

    public Notification(final Unit unit, final NotificationSource source, final Instant startTime,
                        final Instant finishTime, final NotificationStatus status) {
        this.id = null;
        this.unit = unit;
        this.source = source;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.status = status;
    }

    public final Long getUnitId() {
        return unit.getId();
    }

    public final Long getSourceId() {
        return source.getId();
    }
}
