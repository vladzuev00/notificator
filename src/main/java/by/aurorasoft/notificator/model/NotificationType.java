package by.aurorasoft.notificator.model;

public enum NotificationType {
    SENSOR_RANGE_CONTROL, SENSOR_DIFF_CONTROL, SENSOR_NOT_VALID_CONTROL, MAX_SPEED_CONTROL, LOW_BATTERY_CONTROL, POWER_OFF_CONTROL,
    TRIP_DETECT_CONTROL, PARKING_DETECT_CONTROL, IGNITION_OFF_CONTROL, IGNITION_ON_CONTROL,
    DOWNTIME_WITH_IGNITION_CONTROL, ENTRY_GEOFENCE_CONTROL, EXIT_GEOFENCE_CONTROL, STAYING_GEOFENCE_CONTROL,
    GSM_STRENGTH_CONTROL, SATS_STRENGTH_CONTROL, FUEL_LEVEL_CHANGE_CONTROL, GPS_ODOMETER_PERIOD_CONTROL,
    CONNECTION_LOST_CONTROL, ODOMETER_PERIOD_CONTROL
}
