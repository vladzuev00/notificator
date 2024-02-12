package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;

public class NotificationCalcSensorDiffMoreOrEqualsControl extends NotificationCalcSensorDiffAbstractControl {
    public NotificationCalcSensorDiffMoreOrEqualsControl(Meta meta, boolean isContinuable, Sensor sensor, double diff) {
        super(meta, isContinuable, sensor, diff);
    }

    @Override
    protected boolean diffAction(double actualDiff, double expectedDiff) {
        return actualDiff >= expectedDiff;
    }
}
