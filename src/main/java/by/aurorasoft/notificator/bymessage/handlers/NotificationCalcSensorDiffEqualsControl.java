package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;

public class NotificationCalcSensorDiffEqualsControl extends NotificationCalcSensorDiffAbstractControl {
    public NotificationCalcSensorDiffEqualsControl(Meta meta, boolean isContinuable, Sensor sensor, double expectedDiff) {
        super(meta, isContinuable, sensor, expectedDiff);
    }

    @Override
    protected boolean diffAction(double actualDiff, double expectedDiff) {
        return actualDiff == expectedDiff;
    }
}
