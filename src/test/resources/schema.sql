CREATE EXTENSION IF NOT EXISTS Postgis;

CREATE TABLE time_zone
(
    id   INTEGER PRIMARY KEY,
    name VARCHAR(256) NOT NULL UNIQUE
);

CREATE TYPE notification_enabling_state AS ENUM ('ENABLE', 'DISABLE');
CREATE TYPE notification_type AS ENUM ('SENSOR_RANGE_CONTROL', 'SENSOR_DIFF_CONTROL', 'SENSOR_NOT_VALID_CONTROL', 'MAX_SPEED_CONTROL',
    'LOW_BATTERY_CONTROL', 'POWER_OFF_CONTROL', 'TRIP_DETECT_CONTROL', 'PARKING_DETECT_CONTROL',
    'IGNITION_OFF_CONTROL', 'IGNITION_ON_CONTROL', 'DOWNTIME_WITH_IGNITION_CONTROL',
    'ENTRY_GEOFENCE_CONTROL', 'EXIT_GEOFENCE_CONTROL', 'STAYING_GEOFENCE_CONTROL',
    'GSM_STRENGTH_CONTROL', 'SATS_STRENGTH_CONTROL', 'FUEL_LEVEL_CHANGE_CONTROL', 'GPS_ODOMETER_PERIOD_CONTROL',
    'CONNECTION_LOST_CONTROL');
CREATE TYPE notification_method AS ENUM ('PUSH', 'TELEGRAM', 'EMAIL');

CREATE TABLE notification_source
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(50)                 NOT NULL,
    description     TEXT,
    enabling_state  notification_enabling_state NOT NULL DEFAULT 'ENABLE',
    type            notification_type           NOT NULL,
    methods         notification_method[] NOT NULL DEFAULT '{}',
    user_id         INTEGER                     NOT NULL,
    message         VARCHAR(150)                NOT NULL,
    pending_seconds INTEGER                     NOT NULL DEFAULT 0 CHECK (pending_seconds >= 0 AND pending_seconds <= 604800),
    params          JSONB                       NOT NULL,
    time_filter     JSONB                       NOT NULL DEFAULT '{
      "ranges": [
        {
          "from": 0,
          "to": 604799
        }
      ]
    }',
    action_command  JSONB,
    push_enabled    BOOLEAN                     NOT NULL DEFAULT TRUE,
    color           VARCHAR(7),
    deleted         BOOLEAN                     NOT NULL DEFAULT FALSE,
    created         TIMESTAMP(0)                NOT NULL DEFAULT timezone('UTC', now()),
    updated         TIMESTAMP(0)                NOT NULL DEFAULT timezone('UTC', now())
);

CREATE TYPE unit_status AS ENUM ('ACTIVE', 'DISABLED');

CREATE TABLE unit
(
    id      INTEGER PRIMARY KEY,
    name    VARCHAR(256) NOT NULL,
    color   VARCHAR(7)   NOT NULL,
    status  unit_status  NOT NULL,
    deleted BOOLEAN      NOT NULL
);

CREATE TABLE notification_source_unit
(
    source_id BIGINT,
    unit_id   INTEGER,
    created   TIMESTAMP(0) NOT NULL DEFAULT timezone('UTC', now()),
    CONSTRAINT pk_notification_source_unit PRIMARY KEY (source_id, unit_id)
);

ALTER TABLE notification_source_unit
    ADD CONSTRAINT fk_notification_source_unit_to_notification_source
        FOREIGN KEY (source_id)
            REFERENCES notification_source (id);

ALTER TABLE notification_source_unit
    ADD CONSTRAINT fk_notification_source_unit_to_unit
        FOREIGN KEY (unit_id)
            REFERENCES unit (id);

CREATE TABLE geofence
(
    id       INTEGER PRIMARY KEY,
    geometry GEOMETRY NOT NULL
);

CREATE TABLE notification_source_geofence
(
    notification_source_id BIGINT,
    geofence_id            BIGINT,
    created                TIMESTAMP(0) NOT NULL DEFAULT timezone('UTC', now()),
    CONSTRAINT pk_notification_source_geofence PRIMARY KEY (notification_source_id, geofence_id)
);

ALTER TABLE notification_source_geofence
    ADD CONSTRAINT fk_notification_source_geofence_to_notification_source
        FOREIGN KEY (notification_source_id)
            REFERENCES notification_source (id);

ALTER TABLE notification_source_geofence
    ADD CONSTRAINT fk_notification_source_geofence_to_geofence
        FOREIGN KEY (geofence_id)
            REFERENCES geofence (id);

CREATE TYPE notification_status AS ENUM('COMPLETED', 'ACTIVE', 'PENDING', 'CANCELLED', 'DELETED');

CREATE TABLE notification
(
    id          BIGSERIAL PRIMARY KEY,
    source_id   BIGINT              NOT NULL,
    unit_id     INTEGER             NOT NULL,
    start_time  TIMESTAMP(0)        NOT NULL,
    finish_time TIMESTAMP(0),
    status      notification_status NOT NULL,
    is_read     BOOLEAN             NOT NULL DEFAULT FALSE,
    create_time TIMESTAMP(0)                 DEFAULT timezone('UTC', now()),
    update_time TIMESTAMP(0)                 DEFAULT timezone('UTC', now())
);

ALTER TABLE notification
    ADD CONSTRAINT fk_notification_to_notification_source
        FOREIGN KEY (source_id)
            REFERENCES notification_source (id);

ALTER TABLE notification
    ADD CONSTRAINT fk_notification_to_unit
        FOREIGN KEY (unit_id)
            REFERENCES unit (id);

CREATE TABLE telegram
(
    id                       INTEGER PRIMARY KEY,
    user_id                  INTEGER      NOT NULL,
    telegram_chat_id         BIGINT       NOT NULL,
    telegram_user_id         BIGINT       NOT NULL,
    telegram_user_first_name VARCHAR(50),
    telegram_user_last_name  VARCHAR(50),
    telegram_username        VARCHAR(50),
    activated                BOOLEAN      NOT NULL,
    language                 VARCHAR(3)   NOT NULL,
    created                  TIMESTAMP(0) NOT NULL
);

CREATE TABLE notification_source_telegram
(
    notification_source_id INTEGER,
    telegram_id            INTEGER,
    CONSTRAINT pk_notification_source_telegram PRIMARY KEY (notification_source_id, telegram_id)
);

ALTER TABLE notification_source_telegram
    ADD CONSTRAINT fk_notification_source_telegram_to_notification_source
        FOREIGN KEY (notification_source_id)
            REFERENCES notification_source (id);

ALTER TABLE notification_source_telegram
    ADD CONSTRAINT fk_notification_source_telegram_to_telegram
        FOREIGN KEY (telegram_id)
            REFERENCES telegram (id);
