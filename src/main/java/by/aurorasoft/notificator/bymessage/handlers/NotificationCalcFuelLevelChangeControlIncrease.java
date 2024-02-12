package by.aurorasoft.notificator.bymessage.handlers;

import com.locator.server.domain.dto.sensor.Sensor;

public class NotificationCalcFuelLevelChangeControlIncrease extends NotificationCalcFuelLevelChangeControl {

    public NotificationCalcFuelLevelChangeControlIncrease(Meta meta, boolean isContinuable, Sensor sensor, double value) {
        super(meta, isContinuable, sensor, value);
    }

    @Override
    protected boolean compareLevels(double a, double b) {
        return (b - a) > value;
    }
}
