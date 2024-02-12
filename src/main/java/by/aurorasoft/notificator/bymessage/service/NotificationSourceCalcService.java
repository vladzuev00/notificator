package by.aurorasoft.notificator.bymessage.service;

import by.aurorasoft.notificator.bymessage.handlers.NotificationCalc;
import com.locator.server.config.AppCacheConfig;
import com.locator.server.domain.dto.sensor.SensorBunchCalculator;
import com.locator.server.notification.bymessage.creators.NotificationCalcFactory;
import com.locator.server.notification.bymessage.handlers.NotificationCalc;
import com.locator.server.notification.bymessage.vo.Not_NotificationSource;
import com.locator.server.notification.common.service.Not_NotificationSourceService;
import com.locator.server.services.sensor.SensorBunchCalculatorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationSourceCalcService {

    private final Not_NotificationSourceService notificationSourceService;
    private final NotificationCalcFactory notificationCalcFactory;
    private final SensorBunchCalculatorService sensorBunchCalculatorService;

    @Cacheable(value = "notificationCalcCache", cacheManager = AppCacheConfig.CACHE_MANAGER_NAME_CAFFEINE)
    public List<NotificationCalc> list(long unitId) {
        List<Not_NotificationSource> list = notificationSourceService.listEnabled(unitId);
        log.debug("get notification source [count]: {}", list.size());
        SensorBunchCalculator calculator = sensorBunchCalculatorService.get(unitId);
        return notificationCalcFactory.create(unitId, list, calculator);
    }
}
