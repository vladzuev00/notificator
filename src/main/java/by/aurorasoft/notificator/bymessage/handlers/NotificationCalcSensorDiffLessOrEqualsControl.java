package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;

public class NotificationCalcSensorDiffLessOrEqualsControl extends NotificationCalcSensorDiffAbstractControl {
    public NotificationCalcSensorDiffLessOrEqualsControl(Meta meta, boolean isContinuable, Sensor sensor, double diff) {
        super(meta, isContinuable, sensor, diff);
    }

    @Override
    protected boolean diffAction(double actualDiff, double expectedDiff) {
        return actualDiff <= expectedDiff;
    }
}
