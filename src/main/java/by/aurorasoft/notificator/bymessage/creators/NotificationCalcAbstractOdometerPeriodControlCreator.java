package by.aurorasoft.notificator.bymessage.creators;

import by.aurorasoft.notificator.bymessage.handlers.NotificationCalc;

import java.util.Optional;

import static java.lang.Double.MAX_VALUE;


public abstract class NotificationCalcAbstractOdometerPeriodControlCreator extends NotificationCalcCreator {
    protected final boolean continuable;

    public NotificationCalcAbstractOdometerPeriodControlCreator(final boolean continuable){
        this.continuable = continuable;
    }

    @Override
    public Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator) {
        final Optional<SensorCalc> sensorCalcOptional = this.findSensorCalc(calculator);
        return sensorCalcOptional.map(sensorCalc -> {
            if (super.notHas(meta, KEY_PERIOD)) {
                return null;
            }
            final double startValue = this.findStartValue(meta);
            final double finishValue = super.getDouble(meta, KEY_FINISH_VALUE, MAX_VALUE);
            return this.createNotificationCalc(meta, sensorCalc, startValue, finishValue,
                    super.getDouble(meta, KEY_PERIOD));
        });
    }

    protected abstract Optional<SensorCalc> findSensorCalc(SensorBunchCalculator calculator);
    protected abstract NotificationCalc createNotificationCalc(Meta meta, SensorCalc sensorCalc,
                                                               double startValue, double finishValue,
                                                               double period);

    private double findStartValue(Meta meta) {
        double startValue = super.getDouble(meta, KEY_START_VALUE, 0);
        if (startValue < 0) {
            startValue = 0;
        }
        return startValue;
    }
}
