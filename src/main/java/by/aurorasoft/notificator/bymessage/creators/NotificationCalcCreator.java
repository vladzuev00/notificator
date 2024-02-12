package by.aurorasoft.notificator.bymessage.creators;

import by.aurorasoft.notificator.bymessage.handlers.NotificationCalc;
import by.aurorasoft.notificator.model.NotificationType;

import java.util.Optional;

public abstract class NotificationCalcCreator {

    public static final String KEY_MIN = "min";
    public static final String KEY_MAX = "max";

    public static final String KEY_START_VALUE = "startValue";
    public static final String KEY_FINISH_VALUE = "finishValue";
    public static final String KEY_PERIOD = "period";

    public static final String KEY_VALUE = "value";

    public static final String KEY_INCREASE = "increase";
    public static final String KEY_DECREASE = "decrease";
    public static final String KEY_INCREASE_OR_DECREASE = "increaseOrDecrease";

    public static final String KEY_SENSOR_NAME = "sensorName";
    public static final String KEY_GEOFENCE_ID = "geofenceIds";

    public static final String KEY_ACTION = "action";
    public static final String ACTION_WITHIN = "within";
    public static final String ACTION_OUT_OF = "outOf";

    public static final String ACTION_MORE = "more";
    public static final String ACTION_MORE_OR_EQUALS = "moreOrEquals";
    public static final String ACTION_LESS = "less";
    public static final String ACTION_LESS_OR_EQUALS = "lessOrEquals";
    public static final String ACTION_EQUALS = "equals";

    public abstract Optional<NotificationCalc> create(NotificationCalc.Meta meta, SensorBunchCalculator calculator);
    public abstract boolean canCreate(NotificationType notificationType);

    protected double getDouble(NotificationCalc.Meta meta, String key, double defaultValue) {
        Object o = meta.getParams().get(key);
        if (o == null) {
            return defaultValue;
        } else {
            return Double.parseDouble(o.toString());
        }
    }

    protected double getDouble(NotificationCalc.Meta meta, String key) {
        return Double.parseDouble(meta.getParams().get(key).toString());
    }

    protected String getString(NotificationCalc.Meta meta, String key) {
        return meta.getParams().get(key).toString();
    }

    protected Object get(NotificationCalc.Meta meta, String key) {
        return meta.getParams().get(key);
    }

    protected boolean notHas(NotificationCalc.Meta meta, String key) {
        return !meta.getParams().containsKey(key);
    }
}
