package by.aurorasoft.notificator.bytime;

import com.locator.server.notification.bymessage.vo.Not_Notification;
import com.locator.server.notification.bymessage.vo.Not_NotificationSource;
import com.locator.server.notification.bymessage.vo.Not_Unit;
import com.locator.server.notification.common.service.LastValidTimeService;
import com.locator.server.notification.common.service.Not_NotificationServiceManager;
import com.locator.server.notification.common.service.Not_NotificationSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.locator.server.domain.entity.notification.NotificationStatus.ACTIVE;
import static com.locator.server.domain.entity.notification.NotificationType.CONNECTION_LOST_CONTROL;

@Slf4j
@Service
public class LostConnectionNotificationHandler {
    private final Not_NotificationServiceManager manager;
    private final Not_NotificationSourceService sourceService;
    private final LastValidTimeService lastValidTimeService;

    public LostConnectionNotificationHandler(Not_NotificationServiceManager manager,
                                             Not_NotificationSourceService sourceService,
                                             LastValidTimeService lastValidTimeService) {
        this.manager = manager;
        this.sourceService = sourceService;
        this.lastValidTimeService = lastValidTimeService;
    }

    @Scheduled(fixedDelay = 120000)
    public void doWork() {
        try {
            List<Not_NotificationSource> sources = sourceService.list(CONNECTION_LOST_CONTROL);
            Map<Long, Instant> last = lastValidTimeService.map();

            Instant now = Instant.now();
            int activatedCount = 0;
            int deactivateCount = 0;
            for (Not_NotificationSource source : sources) {
                Instant lostBound = now.minusSeconds(source.getPending());
                Instant connectBound = now.minusSeconds(120);
                for (Not_Unit unit : source.getUnits()) {
                    if (last.get(unit.getId()) == null) {
                        continue;
                    }
                    if (isLostConnection(unit, last, lostBound)) {
                        activateNotification(unit, source, last);
                        activatedCount++;
                        continue;
                    }
                    if (isConnect(unit, last, connectBound)) {
                        deactivateCount++;
                        completeNotification(unit, source, last);
                    }
                }
            }
            if (activatedCount != 0 || deactivateCount != 0) {
                log.info("LostConnection activated: {}, deactivated: {}", activatedCount, deactivateCount);
            }
        } catch (Exception e) {
            log.error("Error while handling. Exception: {}, Message: {}", e.getClass().getCanonicalName(), e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isLostConnection(Not_Unit unit, Map<Long, Instant> last, Instant bound) {
        return getLastConnectionTime(unit, last).isBefore(bound);
    }

    private Instant getLastConnectionTime(Not_Unit unit, Map<Long, Instant> last) {
        return last.get(unit.getId());
    }

    private void activateNotification(Not_Unit unit, Not_NotificationSource source, Map<Long, Instant> last) {
        final Not_Notification notification = this.build(source, unit, last);
        this.manager.save(notification);
    }

    private Not_Notification build(Not_NotificationSource source, Not_Unit unit, Map<Long, Instant> last) {
        final Instant startTime = this.getLastConnectionTime(unit, last);
        return new Not_Notification(unit, source, startTime, null, ACTIVE);
    }

    private boolean isConnect(Not_Unit unit, Map<Long, Instant> last, Instant bound) {
        return getLastConnectionTime(unit, last).isAfter(bound);
    }

    private void completeNotification(Not_Unit unit, Not_NotificationSource source, Map<Long, Instant> last) {
        final Instant finishTime = this.getLastConnectionTime(unit, last);
        this.manager.updateToCompleted(unit, source, finishTime);
    }
}
