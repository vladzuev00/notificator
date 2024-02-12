package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;

public class NotificationCalcFuelLevelChangeControlDecrease extends NotificationCalcFuelLevelChangeControl {

    public NotificationCalcFuelLevelChangeControlDecrease(Meta meta, boolean isContinuable, Sensor sensor, double value) {
        super(meta, isContinuable, sensor, value);
    }

    @Override
    protected boolean compareLevels(double a, double b) {
        return (a - b) > value;
    }
}
