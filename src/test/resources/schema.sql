CREATE
EXTENSION IF NOT EXISTS Postgis;

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
    id     SERIAL PRIMARY KEY,
    name   VARCHAR     NOT NULL,
    color  VARCHAR(7)           DEFAULT '#2b8cf7',
    status unit_status NOT NULL DEFAULT 'ACTIVE'
);

CREATE TABLE notification_source_unit
(
    notification_source_id BIGINT,
    unit_id                INTEGER,
    created                TIMESTAMP(0) NOT NULL DEFAULT timezone('UTC', now()),
    CONSTRAINT pk_notification_source_unit PRIMARY KEY (notification_source_id, unit_id)
);

ALTER TABLE notification_source_unit
    ADD CONSTRAINT fk_notification_source_unit_to_notification_source
        FOREIGN KEY (notification_source_id)
            REFERENCES notification_source (id);

ALTER TABLE notification_source_unit
    ADD CONSTRAINT fk_notification_source_unit_to_unit
        FOREIGN KEY (unit_id)
            REFERENCES unit (id);

CREATE TABLE geofence
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR    NOT NULL,
    description VARCHAR(256),
    color       VARCHAR(7) NOT NULL default '#3ec78e',
    max_speed   INTEGER    NOT NULL default 0,
    user_id     BIGINT     NOT NULL,
    boundaries  GEOMETRY,
    CONSTRAINT unique_user_id_name UNIQUE (user_id, name)
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

CREATE TABLE notification_statistic
(
    id                     BIGSERIAL PRIMARY KEY,
    notification_source_id BIGINT              NOT NULL,
    unit_id                INTEGER             NOT NULL,
    start_time             TIMESTAMP(0)        NOT NULL,
    finish_time            TIMESTAMP(0),
    status                 notification_status NOT NULL,
    is_read                BOOLEAN             NOT NULL DEFAULT false,
    created_time           TIMESTAMP(0)                 DEFAULT timezone('UTC', now()),
    updated_time           TIMESTAMP(0)                 DEFAULT timezone('UTC', now())
);

ALTER TABLE notification_statistic
    ADD CONSTRAINT fk_notification_statistic_to_notification_source
        FOREIGN KEY (notification_source_id)
            REFERENCES notification_source (id);

ALTER TABLE notification_statistic
    ADD CONSTRAINT fk_notification_statistic_to_unit
        FOREIGN KEY (unit_id)
            REFERENCES unit (id);

CREATE TABLE telegram
(
    id                       SERIAL PRIMARY KEY,
    user_id                  INTEGER      NOT NULL,
    telegram_chat_id         BIGINT       NOT NULL,
    telegram_user_id         BIGINT       NOT NULL,
    telegram_user_first_name VARCHAR(50),
    telegram_user_last_name  VARCHAR(50),
    telegram_username        VARCHAR(50),
    is_activated             BOOLEAN      NOT NULL DEFAULT 'false',
    language                 VARCHAR(3)   NOT NULL DEFAULT 'ru',
    created                  TIMESTAMP(0) NOT NULL DEFAULT timezone('UTC', now())
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
